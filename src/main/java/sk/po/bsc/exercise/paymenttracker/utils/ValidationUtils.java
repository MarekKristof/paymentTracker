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

    public static boolean isPaymentValid(String command) {
        return PAYMENT_PATTERN.matcher(command.trim()).matches();
    }

    public static boolean isCurrencyKnown(String currency) {
        return Stream.of(ECurrencyCode.values()).map(ECurrencyCode::getName).collect(Collectors.toSet()).contains(currency);
    }

    public static boolean isCommandKnown(String command) {
        return Commands.getCommands().contains(command);
    }
}
