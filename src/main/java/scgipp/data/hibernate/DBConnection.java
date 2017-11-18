package scgipp.data.hibernate;

/**
 * Created by hugo_ on 23/08/2017.
 */
public class DBConnection {

    private static DBManager manager = null;
    private static boolean isActive = false;

    public static void initialize() {
        if (manager == null) manager = new DBManager(new HibernateUtil());
        isActive = true;
    }

    public static DBManager manager() {
        return manager;
    }

    public static void finish() {
        if (manager != null) {
            manager.connection().finish();
            manager = null;
        }
        isActive = false;
    }

    public static boolean isActive() {
        return isActive;
    }

}
