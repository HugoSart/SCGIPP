package scgipp.data.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.supplier_management.Supplier;

import java.util.List;

/**
 * Created by hugo_ on 05/06/2017.
 */
public class SupplierDAO extends DataAccess<Supplier> {

    public SupplierDAO() {
        super();
    }

    @Override
    public Integer add(Supplier supplier) throws ExceptionInInitializerError {

        Transaction transaction = null;
        Integer supplierId = null;

        Session session = sessionFactory.openSession();

        try  {
            transaction = session.beginTransaction();
            supplierId = (Integer) session.save(supplier);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplierId;
    }

    @Override
    public void remove(Integer id) throws ExceptionInInitializerError {

        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            Supplier supplier = session.get(Supplier.class, id);
            session.delete(supplier);
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
    public void update(Supplier supplier) throws ExceptionInInitializerError {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            session.update(supplier);
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
    public Supplier get(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        Supplier supplier = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            supplier = session.get(Supplier.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplier;
    }

    @Override
    public Supplier load(Integer id) throws ExceptionInInitializerError {
        Transaction transaction = null;

        Supplier supplier = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            supplier = session.load(Supplier.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplier;
    }

    @Override
    public List<Supplier> list() throws ExceptionInInitializerError {

        Transaction transaction = null;

        List supplier = null;
        Session session = sessionFactory.openSession();
        try  {
            transaction = session.beginTransaction();
            supplier = session.createQuery("FROM Supplier").list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplier;
    }

}
