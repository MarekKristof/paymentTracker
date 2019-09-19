package sk.po.bsc.exercise;

import sk.po.bsc.exercise.paymenttracker.CommandReader;
import sk.po.bsc.exercise.paymenttracker.InputCommandTracker;
import sk.po.bsc.exercise.paymenttracker.TimeTracker;

/**
 * @author Marek Krištof
 */
public class App {
    public static void main(String[] args) {
        InputCommandTracker inputCommandTracker = new InputCommandTracker();
        TimeTracker         timeTracker    = new TimeTracker();

        CommandReader commandReader = new CommandReader();

        inputCommandTracker.addObserver(commandReader);
        timeTracker.addObserver(commandReader);

        timeTracker.schedule(60);
        inputCommandTracker.createMainLoop();
    }
}

