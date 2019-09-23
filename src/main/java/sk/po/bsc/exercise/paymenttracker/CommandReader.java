package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.data.Payment;
import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.definitions.Messages;
import sk.po.bsc.exercise.paymenttracker.trackers.TimeTracker;
import sk.po.bsc.exercise.paymenttracker.utils.PaymentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;

import static sk.po.bsc.exercise.paymenttracker.definitions.Commands.*;
import static sk.po.bsc.exercise.paymenttracker.definitions.Messages.*;
import static sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils.isCurrencyKnown;

/**
 * @author Marek Krištof
 */
public class CommandReader implements Observer {

    private PaymentCalculator     paymentCalculator     = PaymentCalculator.INSTANCE;
    private PaymentListFileReader paymentListFileReader = PaymentListFileReader.INSTANCE;

    private List<Payment> addedPayments = new ArrayList<>();

    public CommandReader() {
    }

    /**
     * This method is used for managing the commands and payments from observables.
     *
     * @param o   represents observable.
     * @param arg represents argument from observable.
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
        String command = (String) arg;

        switch (command) {
            case TIME_COMMAND:
            case PRINTOUT_COMMAND:
                printStateOfAccount();
                break;
            case QUIT_COMMAND:
                quitProgram(o);
                break;
            default:
                addPayment(command);
        }
    }

    /**
     * This method is used for print out state of account. It starts with list of all payments.
     * Then it print calculated payments, and then it print calculated payments with exchange rate calculation.
     */
    private void printStateOfAccount() {
        System.out.println(LINE_BREAK + NEW_LINE + Messages.MSG_PAYMENTS_INPUT);
        List<Payment>                  payments           = paymentListFileReader.readFileWithPayments();
        Map<ECurrencyCode, BigDecimal> calculatedPayments = paymentCalculator.doCalculation(combineListsOfPayments(payments, addedPayments));

        combineListsOfPayments(payments, addedPayments).forEach(System.out::println);

        System.out.println(NEW_LINE + Messages.MSG_PAYMENTS_OUTPUT);
        calculatedPayments.forEach((k, v) -> {
            if (v.compareTo(BigDecimal.ZERO) != 0) {
                System.out.println(k + " " + v);
            }
        });

        System.out.println(NEW_LINE + Messages.MSG_PAYMENTS_OUTPUT_USD);
        calculatedPayments.forEach((k, v) -> {
            if (v.compareTo(BigDecimal.ZERO) != 0) {
                if (k.equals(ECurrencyCode.USD)) {
                    System.out.println(k + " " + v);
                } else {
                    System.out.println(k + " " + v + "(USD " +
                            v.multiply(BigDecimal.valueOf(k.getExchangeRate())).setScale(2, RoundingMode.HALF_UP) + ")");
                }
            }
        });

        System.out.println(LINE_BREAK);
    }

    /**
     * This method is used for combination lists of payments from file and memory.
     *
     * @param addedPayments    represents list of payments from memory.
     * @param paymentsFromFile represents list of payments from file.
     * @return list of payments, which consist from combination of param lists.
     */
    private static List<Payment> combineListsOfPayments(List<Payment> addedPayments, List<Payment> paymentsFromFile) {
        List<Payment> combinedList = new ArrayList<>();
        Stream.of(addedPayments, paymentsFromFile).forEach(combinedList::addAll);
        return combinedList;
    }

    /**
     * This method is used for adding payment from input to memory.
     *
     * @param payment represents input from input.
     */
    private void addPayment(String payment) {
        String     currency       = payment.substring(0, 3);
        String     ammountOfMoney = payment.substring(4);
        BigDecimal bd             = new BigDecimal(ammountOfMoney);

        if (isCurrencyKnown(currency)) {
            addedPayments.add(new Payment(ECurrencyCode.valueOf(currency), bd));
            System.out.println(MSG_YOUR_PAYMENT_WAS_SUCCESFULLY_ADDED);
        } else {
            System.out.println(MSG_CURRENCY_IS_NOT_VALID + " " + currency);
            System.out.println(MSG_AVAILABLE_CURRENCIES + " " + Arrays.asList(ECurrencyCode.values()));
        }
    }

    /**
     * This method is used for finishing the program. It stop the timer, delete all observers and exit with value 0.
     *
     * @param o represents observable.
     */
    private void quitProgram(java.util.Observable o) {
        if (o instanceof TimeTracker) {
            ((TimeTracker) o).stop();
        }
        o.deleteObservers();
        System.exit(0);
    }

}
