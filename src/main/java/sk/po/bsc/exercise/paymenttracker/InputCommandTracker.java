package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.Payment;

import java.io.*;
import java.util.*;

/**
 * @author Marek Krištof
 */
public class InputCommandTracker extends Observable {


    private final Scanner scanner;

    public InputCommandTracker() {
        scanner = new Scanner(System.in);
    }

    public void createMainLoop() {
       // while (scanner.hasNextLine()) {
            setChanged();
            notifyObservers(scanner.nextLine());
        //}
    }


}
