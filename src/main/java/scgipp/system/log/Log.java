package scgipp.system.log;

import java.util.Calendar;

/**
 * Created by hsart on 13/05/17.
 */
public class Log {

    private static boolean isEnabled = true;

    public static void enable(boolean b) {
        isEnabled = true;
    }

    public static void show(String tag, String message) {
        if (isEnabled)
            System.out.println(Calendar.getInstance().getTime() + ": " + tag + ": " + message);
    }

    public static void show(String tag, String info, String message) {
        if (isEnabled)
            System.out.println(Calendar.getInstance().getTime() + ": " + tag.toString() + ": " + info + " -> " + message);
    }

}
