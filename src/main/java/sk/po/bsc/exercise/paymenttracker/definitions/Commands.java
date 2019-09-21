package sk.po.bsc.exercise.paymenttracker.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marek Krištof
 * Created: 21-Sep-19
 */
public class Commands {

    public static final String TIME_COMMAND     = "TIME";
    public static final String QUIT_COMMAND     = "QUIT";
    public static final String PRINTOUT_COMMAND = "PRINTOUT";

    private static final List<String> commands = new ArrayList<>();


    static {
        commands.add(TIME_COMMAND);
        commands.add(QUIT_COMMAND);
        commands.add(PRINTOUT_COMMAND);
    }


    public static List<String> getCommands() {
        return commands;
    }
}
