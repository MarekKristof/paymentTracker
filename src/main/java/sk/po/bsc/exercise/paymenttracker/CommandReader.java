package sk.po.bsc.exercise.paymenttracker;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sk.po.bsc.exercise.paymenttracker.data.Payment;
import sk.po.bsc.exercise.paymenttracker.definitions.Commands;
import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.definitions.Messages;
import sk.po.bsc.exercise.paymenttracker.utils.PaymentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;

import static sk.po.bsc.exercise.paymenttracker.definitions.Messages.*;
import static sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils.isCurrencyKnown;

/**
 * @author Marek Krištof
 */
public class CommandReader implements Observer {

    private PaymentCalculator     paymentCalculator     = PaymentCalculator.INSTANCE;
    private PaymentListFileReader paymentListFileReader = PaymentListFileReader.INSTANCE;

    private List<Payment> addedPayments = new ArrayList<>();

    @Override
    public void update(java.util.Observable o, Object arg) {
        String command = (String) arg;

        switch (command) {
            case Commands.TIME_COMMAND:
                printStateOfAccount();
                break;
            case Commands.QUIT_COMMAND:
                quitProgram();
                break;
            default:
                addPayment(command);
        }
    }

    private void printStateOfAccount() {
        System.out.println(LINE_BREAK + NEW_LINE + Messages.MSG_PAYMENTS_INPUT);
        List<Payment>                  payments           = paymentListFileReader.readFileWithPayments();
        Map<ECurrencyCode, BigDecimal> calculatedPayments = paymentCalculator.doCalculation(combineListsOfPayments(payments, addedPayments));

        combineListsOfPayments(payments, addedPayments).forEach(System.out::println);

        System.out.println(NEW_LINE + Messages.MSG_PAYMENTS_OUTPUT);
        calculatedPayments.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println(NEW_LINE + Messages.MSG_PAYMENTS_OUTPUT_USD);
        calculatedPayments.forEach((k, v) -> System.out.println(k + " " + v + "(USD " +
                v.multiply(new BigDecimal(k.getExchangeRate())).setScale(2, RoundingMode.HALF_UP) + ")"));

        System.out.println(LINE_BREAK);
    }

    private static List<Payment> combineListsOfPayments(List<Payment> addedPayments, List<Payment> paymentsFromFile) {
        List<Payment> combinedList = new ArrayList<>();
        Stream.of(addedPayments, paymentsFromFile).forEach(combinedList::addAll);
        return combinedList;
    }

    private void addPayment(String command) {
        String     currency       = command.substring(0, 3);
        String     ammountOfMoney = command.substring(4, command.length() - 1).replaceAll(",", "");
        BigDecimal bd             = new BigDecimal(ammountOfMoney);

        if (isCurrencyKnown(currency)) {
            addedPayments.add(new Payment(ECurrencyCode.valueOf(currency), bd));
            System.out.println(MSG_YOUR_PAYMENT_WAS_SUCCESFULLY_ADDED);
        } else {
            System.out.println(MSG_CURRENCY_IS_NOT_VALID + " " + currency);
            System.out.println(MSG_AVAILABLE_CURRENCIES + " " + Arrays.asList(ECurrencyCode.values()));
        }
    }

    private void quitProgram() {
    }

}
