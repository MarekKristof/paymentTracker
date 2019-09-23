package sk.po.bsc.exercise.paymenttracker.trackers;

import sk.po.bsc.exercise.paymenttracker.definitions.Commands;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Marek Krištof
 */
public class TimeTracker extends Observable {

    private Timer timer;

    public TimeTracker() {
        timer = new Timer();
    }

    /**
     * This method is used for schedule one minute loop. Program after one minute notify observer.
     *
     * @param seconds represents amount of seconds for one interval.
     */
    public void schedule(long seconds) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers(Commands.TIME_COMMAND);
            }
        }, 0, seconds * 1000);

    }

    /**
     * This method is used to stop the timer.
     */
    public void stop() {
        timer.cancel();
    }
}
