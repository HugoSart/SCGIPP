package scgipp.data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 *   Classe esqueleto para a criação de uma classe do tipo Data Access Object
 */

public abstract class DataAccess<T> {

    protected static SessionFactory sessionFactory = null;

    protected DataAccess() throws ExceptionInInitializerError {
        try {
            if (sessionFactory == null || sessionFactory.isClosed())
                sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void close() {
        sessionFactory.close();
    }
    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public abstract Integer add(T object) throws ExceptionInInitializerError;
    public abstract void remove(Integer id) throws ExceptionInInitializerError;
    public abstract void update(T object) throws ExceptionInInitializerError;
    public abstract T get(Integer id) throws ExceptionInInitializerError;
    public abstract T load(Integer id) throws ExceptionInInitializerError;
    public abstract List<T> list() throws ExceptionInInitializerError;

}
