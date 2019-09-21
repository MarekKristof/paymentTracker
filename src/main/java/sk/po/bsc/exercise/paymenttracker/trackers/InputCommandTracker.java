package sk.po.bsc.exercise.paymenttracker.trackers;

import sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils;

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
        while (true) {
            if (ValidationUtils.isPaymentValid(scanner.nextLine())) {
                setChanged();
                notifyObservers(scanner.nextLine());
            }
        }
    }


}
