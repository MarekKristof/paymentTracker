package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.Payment;

import java.io.*;
import java.util.*;

/**
 * @author Marek Krištof
 */
public class PaymentTracker extends Observable {

    private static final String PAYMENTS_FILE = "/Paymments.txt";

    private Scanner scanner;

    public PaymentTracker() {
        scanner = new Scanner(System.in);
    }

    public List<Payment> readFileWithPayments() {
        List<Payment> payments = new ArrayList<Payment>();


        File file = new File(PAYMENTS_FILE);
        if (file.exists() && !file.isDirectory()) {
            try {
                FileReader fr = new FileReader(file);


                BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(PAYMENTS_FILE)));

                //br returns as stream and convert it into a List
                // payments = br.lines().collect(Collectors.toList());

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Súbor " + PAYMENTS_FILE + " nebol nájdený.");
            readLine("Chcete pokračovať vytvorením súboru? Prosím zadajte ANO/NIE. ");
        }


        payments.forEach(System.out::println);


        return payments;
    }


    public void createMainLoop() {
        String command;
        //while (scanner.hasNextLine()) {
              setChanged();
              notifyObservers(scanner.nextLine());
       // }

    }


    private String readLine(String message) {
        String s = "";
        System.out.println(message);
        s = scanner.nextLine();
        return s;
    }

}
