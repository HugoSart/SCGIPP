package scgipp.data.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.bill_management.Bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kira on 01/08/17.
 */
public class BillDAO extends DataAccess<Bill> {

    public BillDAO(){
        super();
    }

    @Override
    public Integer add(Bill novaConta) throws ExceptionInInitializerError
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer contaId = null;

        try
        {
            tx = session.beginTransaction();
            contaId = (Integer)session.save(novaConta);
            tx.commit();
        }
        catch (HibernateException error)
        {
            if (tx == null) tx.rollback();
            error.printStackTrace();
        }
        finally
        {
            session.close();
        }

        return contaId;
    }

    @Override
    public void remove(Integer removeId) throws ExceptionInInitializerError
    {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Bill billAlvo = session.get(Bill.class, removeId);
            session.delete(billAlvo);
            tx.commit();
        }
        catch(HibernateException error)
        {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Bill get(Integer billId) throws ExceptionInInitializerError
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Bill billAlvo = null;
        try
        {
            tx = session.beginTransaction();
            billAlvo = session.get(Bill.class, billId);
            tx.commit();
        }
        catch (HibernateException error)
        {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally
        {
            session.close();
        }

        return billAlvo;
    }

    @Override
    public Bill load(Integer idAlvo) throws ExceptionInInitializerError
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Bill billAlvo = null;
        try
        {
            tx = session.beginTransaction();
            billAlvo = session.load(Bill.class, idAlvo);
            tx.commit();
        }
        catch (HibernateException error)
        {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return billAlvo;
    }

    @Override
    public void update(Bill billAlvo) throws ExceptionInInitializerError
    {
        /*
         * Metodo para atualizar cadastro de produto
         *
         * @args:
         * prodAlvo: o produto a ser atualizado
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.update(billAlvo);
            tx.commit();
            session.close();
        }
        catch (HibernateException error)
        {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }


    /**
     * Metodo para listar todos os produtos cadastrados
     *
     * @return todosProd: Lista contendo todos os produtos cadastrados
     */
    @Override
    public List<Bill> list() throws ExceptionInInitializerError
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List todasBills = new ArrayList();
        try
        {
            tx = session.beginTransaction();
            todasBills = session.createQuery("FROM Bill").list();
            tx.commit();
        }
        catch (HibernateException error)
        {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        } finally {
            session.close();
        }

        return todasBills;
    }


}