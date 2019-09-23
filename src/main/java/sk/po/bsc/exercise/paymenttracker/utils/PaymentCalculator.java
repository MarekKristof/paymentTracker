package sk.po.bsc.exercise.paymenttracker.utils;

import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.data.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Marek Krištof
 */
public enum PaymentCalculator {
    INSTANCE;

    /**
     * This method calculate all payments and return summarization of them.
     *
     * @param listOfAllPayments represents lis of all payments. Including payments from file and payments from memory.
     * @return Map<ECurrencyCode, BigDecimal> represents map, where key is currency and value is amount of money.
     */
    public static Map<ECurrencyCode, BigDecimal> doCalculation(List<Payment> listOfAllPayments) {
        Map<ECurrencyCode, BigDecimal> groupedPayments = listOfAllPayments.stream()
                .collect(Collectors.groupingBy(Payment::getCurrency,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)));
        return groupedPayments;
    }
}
