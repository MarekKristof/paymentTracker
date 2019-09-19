package sk.po.bsc.exercise.paymenttracker;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marek Krištof
 * Created: 19-Sep-19
 */
public enum PaymentListFileReader {
    INSTANCE;

    private static final String PAYMENTS_FILE = "Payments.txt";

    private PaymentListFileReader() {
    }

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
                    payments.add(new Payment(getECurencyCode(line.substring(0,3)), new BigDecimal(line.substring(4 ,line.length()-1))));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
      //  } else {
       //     System.out.println("Súbor " + PAYMENTS_FILE + " nebol nájdený.");
        //}
        return payments;
    }

    private static ECurrencyCode getECurencyCode(String line){
        return ECurrencyCode.valueOf(line.trim());
        //TODO NEZNAMA MENA
    }

}
