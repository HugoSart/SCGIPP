package scgipp.data.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by hsart on 09/05/17.
 */
public abstract class DataAccess<T> {

    protected SessionFactory sessionFactory;

    protected DataAccess() throws ExceptionInInitializerError {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public abstract Integer add(T object) throws ExceptionInInitializerError;
    public abstract void remove(Integer id) throws ExceptionInInitializerError;
    public abstract void update(T object) throws ExceptionInInitializerError;
    public abstract T get(Integer id) throws ExceptionInInitializerError;
    public abstract List<T> list() throws ExceptionInInitializerError;

}
