package scgipp.data.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.product_management.Product;
import scgipp.service.sale_management.Sale;
import scgipp.service.sale_management.SaleBudget;

import java.util.ArrayList;
import java.util.List;

public class SaleDAO extends DataAccess<Sale> {

    public SaleDAO(){
        super();
    }

    @Override
    public Integer add(Sale newProduct)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer productId = null;

        try{
            tx = session.beginTransaction();
            productId = (Integer)session.save(newProduct);
            tx.commit();
        }
        catch (HibernateException error){
            if (tx == null) tx.rollback();
            error.printStackTrace();
        }
        finally{
            session.close();
        }

        return productId;
    }

    @Override
    public void remove(Integer removeId)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Sale prodAlvo = session.get(Sale.class, removeId);
            session.delete(prodAlvo);
            tx.commit();
        } catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Sale get(Integer prodId)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Sale prodAlvo = null;
        try {
            tx = session.beginTransaction();
            prodAlvo = session.get(Sale.class, prodId);
            tx.commit();
        }
        catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally {
            session.close();
        }

        return prodAlvo;
    }

    @Override
    public Sale load(Integer idAlvo)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Sale prodAlvo = null;
        try  {
            tx = session.beginTransaction();
            prodAlvo = session.load(Sale.class, idAlvo);
            tx.commit();
        }
        catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally {
            session.close();
        }
        return prodAlvo;
    }

    @Override
    public void update(Sale prodAlvo)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(prodAlvo);
            tx.commit();
            session.close();
        }
        catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally {
            session.close();
        }

    }

    @Override
    public List<Sale> list() throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List todosProd = new ArrayList();
        try{
            tx = session.beginTransaction();
            todosProd = session.createQuery("FROM Sale").list();
            tx.commit();
        } catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        } finally {
            session.close();
        }

        return todosProd;
    }


}
