package scgipp.data.hibernate;

/**
 * Created by hugo_ on 23/08/2017.
 */
public class DBConnection {

    private static DBManager manager = null;

    public static void initialize() {
        if (manager == null) manager = new DBManager(new HibernateUtil());
    }

    public static DBManager manager() {
        initialize();
        return manager;
    }

    public static void finish() {
        if (manager != null) manager.connection().finish();
    }

}
