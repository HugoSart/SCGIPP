package scgipp.data.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.user_management.User;

import java.util.List;

public class UserDAO extends DataAccess<User> {

    public UserDAO() {
        super();
    }

    @Override
    public Integer add(User user) throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer userId = null;

        try {
            transaction = session.beginTransaction();
            userId = (Integer)session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userId;
    }

    @Override
    public void remove(Integer id) throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void remove(String login) throws ExceptionInInitializerError {

        Integer id = null;

        for (User user : list()) {
            if (user.getLogin().equals(login)) {
                id = user.getId();
                break;
            }
        }

        remove(id);

    }

    @Override
    public void update(User user) throws ExceptionInInitializerError {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public User get(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        User user = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public User get(String login) throws ExceptionInInitializerError {
        List list = list();

        User user = null;

        for (Object ob : list) {
            if (((User)ob).getLogin().equals(login))
                user = (User)ob;
        }

        return user;

    }

    @Override
    public User load(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        User user = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            user = session.load(User.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public List<User> list() throws ExceptionInInitializerError {

        Transaction transaction = null;

        List users = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users;
    }

}
