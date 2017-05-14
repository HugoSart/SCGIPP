package scgipp.service.user_management;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;

import java.util.List;

/**
 * Created by hsart on 10/05/17.
 */
class UserDAO extends DataAccess<User> {

    public UserDAO() {
        super();
    }

    @Override
    public Integer add(User user) throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer userId = null;

        try{
            transaction = session.beginTransaction();
            userId = (Integer)session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return userId;
    }

    @Override
    public void remove(Integer id) throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void remove(String login) throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            User user = (User)session.createQuery("FROM User WHERE login = " + login).getSingleResult();
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(User user) throws ExceptionInInitializerError {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public User get(Integer id) throws ExceptionInInitializerError {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        User user = null;

        try{
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
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
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        User user = null;

        try{
            transaction = session.beginTransaction();
            user = session.load(User.class, id);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return user;
    }

    @Override
    public List<User> list() throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        List users = null;

        try{
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return users;
    }

}
