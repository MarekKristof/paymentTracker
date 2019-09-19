package sk.po.bsc.exercise.paymenttracker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Marek Krištof
 */
public enum PaymentCalculator {
    INSTANCE;

    private PaymentCalculator() {
    }

    public static Map<ECurrencyCode, BigDecimal> doCalculation(List<Payment> listOfAllPayments) {
        Map<ECurrencyCode, BigDecimal> groupedPayments = listOfAllPayments.stream()
                .collect(Collectors.groupingBy(Payment::getCurrency,
                        Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)));
        return groupedPayments;
    }
}
