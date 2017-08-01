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

/**
 * Created by kira on 05/06/17.
 */
public class SaleBudgetDAO extends DataAccess<SaleBudget> {

    public SaleBudgetDAO(){
        super();
    }

    @Override
    public Integer add(SaleBudget newProduct)
            throws ExceptionInInitializerError{

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
            throws ExceptionInInitializerError{

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            SaleBudget prodAlvo = session.get(SaleBudget.class, removeId);
            session.delete(prodAlvo);
            tx.commit();
        }
        catch(HibernateException error){
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public void remove(String removeNome){

        Integer idAlvo = null;

        for (SaleBudget prodAlvo : list()) {
            if (prodAlvo.name.equals(removeNome)) {
                idAlvo = prodAlvo.getId();
                break;
            }
        }
        remove(idAlvo);
    }

    @Override
    public SaleBudget get(Integer prodId)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        SaleBudget prodAlvo = null;
        try {
            tx = session.beginTransaction();
            prodAlvo = session.get(SaleBudget.class, prodId);
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

    public SaleBudget get(String nomeAlvo)
            throws ExceptionInInitializerError {

        SaleBudget prodAlvo = null;
        List prodCadastrado = list();
        for (Object objIterator : prodCadastrado) {
            if (((SaleBudget)objIterator).name.equals(nomeAlvo)) {
                prodAlvo = ((SaleBudget)objIterator);
                break;
            }
        }
        return prodAlvo;
    }

    @Override
    public SaleBudget load(Integer idAlvo)
            throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        SaleBudget prodAlvo = null;
        try  {
            tx = session.beginTransaction();
            prodAlvo = session.load(SaleBudget.class, idAlvo);
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
    public void update(SaleBudget prodAlvo)
            throws ExceptionInInitializerError {
        /*
         * Metodo para atualizar cadastro de produto
         *
         * @args:
         * prodAlvo: o produto a ser atualizado
         */

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


    /**
    * Metodo para listar todos os produtos cadastrados
    *
    * @return todosProd: Lista contendo todos os produtos cadastrados
    */
    @Override
    public List<SaleBudget> list() throws ExceptionInInitializerError {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List todosProd = new ArrayList();
        try{
            tx = session.beginTransaction();
            todosProd = session.createQuery("FROM SaleBudget").list();
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
