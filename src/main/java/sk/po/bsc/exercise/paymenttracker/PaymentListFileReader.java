package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.data.Payment;
import sk.po.bsc.exercise.paymenttracker.definitions.Messages;
import sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marek Krištof
 */
public enum PaymentListFileReader {
    INSTANCE;

    private static final String PAYMENTS_FILE = "Payments.txt";

    public static List<Payment> readFileWithPayments() {
        List<Payment> payments = new ArrayList<Payment>();


        // File file = new File(PAYMENTS_FILE);
        // if (file.exists() && !file.isDirectory()) {
        try {
            // FileReader fr = new FileReader(file);

            ClassLoader classLoader = PaymentListFileReader.class.getClassLoader();

            BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(PAYMENTS_FILE)));

            String line;
            while ((line = br.readLine()) != null) {
                if (ValidationUtils.isPaymentValid(line)) {
                    payments.add(new Payment(getECurencyCode(line.substring(0, 3)), new BigDecimal(line.substring(4, line.length() - 1))));
                } else {
                    System.out.println(Messages.MSG_PAYMENT_IS_NOT_VALID);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  } else {
        //     System.out.println("Súbor " + PAYMENTS_FILE + " nebol nájdený.");
        //}
        return payments;
    }

    private static ECurrencyCode getECurencyCode(String line) {
        return ECurrencyCode.valueOf(line.trim());
        //TODO NEZNAMA MENA
    }

}