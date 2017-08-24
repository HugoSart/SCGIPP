package scgipp.data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.Nullable;

/**
 * Created by hsart on 13/05/17.
 */
public class HibernateUtil {

    private SessionFactory sessionFactory = null;

    public HibernateUtil(@Nullable String cfgPath) {
        try {
            if (sessionFactory == null || sessionFactory.isClosed()) {
                if (cfgPath != null) sessionFactory = new Configuration().configure(cfgPath).buildSessionFactory();
                else sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Throwable e) {
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public HibernateUtil() {
        this(null);
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void finish() {
        sessionFactory.close();
    }

}
