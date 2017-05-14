package scgipp.system.log;

/**
 * Created by hsart on 13/05/17.
 */
public class Log {

    public enum Type {
        INFO, WARNING, ERROR
    }

    private static boolean isEnabled = true;

    public static void enable(boolean b) {
        isEnabled = true;
    }

    public static void show(Type type, String message) {
        if (isEnabled)
            System.out.println(type.toString() + ": " + message);
    }

    public static void show(Type type, String info, String message) {
        if (isEnabled)
            System.out.println(type.toString() + ": " + info + " - " + message);
    }

}
