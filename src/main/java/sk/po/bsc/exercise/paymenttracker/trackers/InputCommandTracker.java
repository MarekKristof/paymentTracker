package sk.po.bsc.exercise.paymenttracker.trackers;


import sk.po.bsc.exercise.paymenttracker.utils.ValidationUtils;

import java.util.*;

import static sk.po.bsc.exercise.paymenttracker.definitions.Messages.MSG_PAYMENT_IS_NOT_VALID;

/**
 * @author Marek Krištof
 */
public class InputCommandTracker extends Observable {


    public InputCommandTracker() {
    }

    /**
     * This method is used for creating main loop, which will handle input command.
     */
    public void createMainLoop() {
        Runnable commandHandlingTask = () ->
        {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String command = scanner.nextLine();
                    handleCommand(command);
                }
            }
        };
        new Thread(commandHandlingTask).start();
    }

    /**
     * This method handle command, and validate if command is valid payment or known command.
     * After successful validation, it will notify observer.
     *
     * @param command represents command to handle.
     */
    private void handleCommand(String command) {
        if (command != null) {
            String inputCommand = command.trim();
            if (ValidationUtils.isPaymentValid(inputCommand) || ValidationUtils.isCommandKnown(command)) {
                setChanged();
                notifyObservers(inputCommand);
            } else {
                System.out.println(MSG_PAYMENT_IS_NOT_VALID + " " + inputCommand);
            }
        }
    }


}
