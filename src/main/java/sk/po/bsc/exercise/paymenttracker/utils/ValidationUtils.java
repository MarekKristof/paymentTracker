package sk.po.bsc.exercise.paymenttracker.utils;

import sk.po.bsc.exercise.paymenttracker.definitions.Commands;
import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marek Krištof
 */
public class ValidationUtils {

    private static final Pattern PAYMENT_PATTERN = Pattern.compile("([A-Z]{3}) (-?\\d+(?:[.]\\d{0,2})?)");

    private ValidationUtils() {
    }

    /**
     * This method is used for validating the payment, if payment has appropriate format.
     *
     * @param payment which will be validated.
     * @return true, if payment is valid. Otherwise false.
     */
    public static boolean isPaymentValid(String payment) {
        return PAYMENT_PATTERN.matcher(payment.trim()).matches();
    }

    /**
     * This method is used for checking the input currency for payment.
     *
     * @param currency represents currency, which will be check, if it is known currency.
     * @return true, if currency is known. Otherwise false.
     */
    public static boolean isCurrencyKnown(String currency) {
        return Stream.of(ECurrencyCode.values()).map(ECurrencyCode::getName).collect(Collectors.toSet()).contains(currency);
    }

    /**
     * This method is used for checking the input command.
     *
     * @param command represents command, which will be check, if it is known command.
     * @return true, if command is known. Otherwise false.
     */
    public static boolean isCommandKnown(String command) {
        return Commands.getCommands().contains(command);
    }
}
