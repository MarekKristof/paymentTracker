package sk.po.bsc.exercise.paymenttracker.utils;

import java.util.regex.Pattern;

/**
 * @author Marek Krištof
 */
public class ValidationUtils {

    private static final Pattern PAYMENT_PATTERN = Pattern.compile("([A-Z]{3}) (-?\\d+(?:[.,]\\d{2})?)");

    private ValidationUtils(){}

    public static boolean isPaymentValid(String command) {
        return PAYMENT_PATTERN.matcher(command.trim()).matches();
    }
}
