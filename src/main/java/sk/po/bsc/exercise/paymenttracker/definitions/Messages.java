package sk.po.bsc.exercise.paymenttracker.definitions;

/**
 * @author Marek Krištof
 * Created: 19-Sep-19
 */
public class Messages {

    private Messages() {
    }

    public static final String NEW_LINE   = "\n";
    public static final String LINE_BREAK = "-------------------------------------------------------------";

    public static final String MSG_LOAD_DATA_PROBLEM                  = "There occurs problem with loading data from file. Please check if resources directory contains this file:";
    public static final String MSG_AVAILABLE_CURRENCIES               = "Available currencies: ";
    public static final String MSG_YOUR_PAYMENT_WAS_SUCCESFULLY_ADDED = "Your payment was succesfully added";
    public static final String MSG_CURRENCY_IS_NOT_VALID              = "Invalid currency: ";
    public static final String MSG_PAYMENT_IS_NOT_VALID               = "Unknown command: ";
    public static final String MSG_PAYMENTS_INPUT                     = "All payments:";
    public static final String MSG_PAYMENTS_OUTPUT                    = "Payments summarization:";
    public static final String MSG_PAYMENTS_OUTPUT_USD                = "Payments summarization in USD:";

}
