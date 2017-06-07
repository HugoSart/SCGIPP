package scgipp.data.hibernate.specification;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.customer_management.Customer;
import scgipp.service.user_management.User;

import java.util.List;
public class CustomerDAO extends DataAccess<Customer> {

    public CustomerDAO() {
        super();
    }

    /**
     *  Método para adicionar um cliente na database
     *  @arg    Objeto do cliente a ser adicionado
     *  @return Id do cliente
     */
    @Override
    public Integer add(Customer customer) throws ExceptionInInitializerError {

        Transaction transaction = null;
        Integer customerId = null;

        Session session = sessionFactory.openSession();

        try  {
            transaction = session.beginTransaction();
            customerId = (Integer) session.save(customer);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customerId;
    }

    @Override
    public void remove(Integer id) throws ExceptionInInitializerError {

        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
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
    public void update(Customer customer) throws ExceptionInInitializerError {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            session.update(customer);
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
    public Customer get(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        Customer customer = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            customer = session.get(Customer.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customer;
    }

    @Override
    public Customer load(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        Customer customer = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            customer = session.load(Customer.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customer;
    }

    @Override
    public List<Customer> list() throws ExceptionInInitializerError {

        Transaction transaction = null;

        List customers = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            customers = session.createQuery("FROM Customer").list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customers;
    }

}
