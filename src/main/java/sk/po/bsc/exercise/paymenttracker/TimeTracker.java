package sk.po.bsc.exercise.paymenttracker;

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


    public void schedule(long seconds) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setChanged();
                notifyObservers("TIME");
            }
        }, 0, seconds * 1000);

    }

    public void stop() {
        timer.cancel();
    }
}
