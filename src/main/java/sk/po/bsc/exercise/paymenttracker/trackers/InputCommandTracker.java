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

    public void createMainLoop() {
        Runnable commandHandlingTask = () ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                while (true)
                {
                    String command = scanner.nextLine();
                    handleCommand(command);
                }
            }
        };
        new Thread(commandHandlingTask).start();
    }

    private void handleCommand(String command){
        String inputCommand = command.trim();
        if (ValidationUtils.isPaymentValid(inputCommand)) {
            setChanged();
            notifyObservers(inputCommand);
        } else {
            System.out.println(MSG_PAYMENT_IS_NOT_VALID + " " + inputCommand);
        }
    }


}
