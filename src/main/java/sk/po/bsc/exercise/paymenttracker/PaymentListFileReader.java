package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.data.Payment;
import sk.po.bsc.exercise.paymenttracker.definitions.Messages;
import sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static sk.po.bsc.exercise.paymenttracker.definitions.Messages.MSG_LOAD_DATA_PROBLEM;

/**
 * @author Marek Krištof
 */
public enum PaymentListFileReader {
    INSTANCE;

    private static final String PAYMENTS_FILE = "Payments.txt";

    /**
     * This program is used for read payments from file.
     *
     * @return list of payments from file.
     */
    public static List<Payment> readFileWithPayments() {
        List<Payment> payments = new ArrayList<Payment>();

        BufferedReader br = null;

        try {

            ClassLoader classLoader = PaymentListFileReader.class.getClassLoader();

            InputStream resourceAsStream = classLoader.getResourceAsStream(PAYMENTS_FILE);
            if (resourceAsStream != null) {
                br = new BufferedReader(new InputStreamReader(resourceAsStream));

                String line;
                while ((line = br.readLine()) != null) {
                    if (ValidationUtils.isPaymentValid(line)) {
                        payments.add(new Payment(getECurencyCode(line.substring(0, 3)), new BigDecimal(line.substring(4))));
                    } else {
                        System.out.println(Messages.MSG_PAYMENT_IS_NOT_VALID);
                    }
                }
                br.close();
            } else {
                System.out.println(MSG_LOAD_DATA_PROBLEM + " " + PAYMENTS_FILE);
            }

        } catch (IOException e) {
            System.out.println(MSG_LOAD_DATA_PROBLEM + " " + PAYMENTS_FILE);
        }
        return payments;
    }

    /**
     * This method is used for returning ECurrencyCde value by input commandFromCode.
     *
     * @param commandForCode represents command in String format from input for currency.
     * @return ECurrency value.
     */
    private static ECurrencyCode getECurencyCode(String commandForCode) {
        return ECurrencyCode.valueOf(commandForCode.trim());
    }

}
