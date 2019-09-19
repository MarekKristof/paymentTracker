package sk.po.bsc.exercise.paymenttracker;


import java.util.Observer;

/**
 * @author Marek Krištof
 */
public class CommandReader implements Observer {

    @Override
    public void update(java.util.Observable o, Object arg) {
        System.out.println("CommandReader got new command:"+(String)arg);
    }

    private boolean verifyCommand(){
        boolean isCorrect = false;

        return isCorrect;
    }
}
