package sk.po.bsc.exercise;

import sk.po.bsc.exercise.paymenttracker.CommandReader;
import sk.po.bsc.exercise.paymenttracker.PaymentTracker;
import sk.po.bsc.exercise.paymenttracker.TimeTracker;

/**
 * @author Marek Krištof
 */
public class App {
    public static void main(String[] args) {
        PaymentTracker paymentTracker = new PaymentTracker();
        TimeTracker    timeTracker    = new TimeTracker();

        paymentTracker.readFileWithPayments();

        CommandReader commandReader = new CommandReader();

        paymentTracker.addObserver(commandReader);
        timeTracker.addObserver(commandReader);

        timeTracker.schedule(60);
        paymentTracker.createMainLoop();
    }
}

