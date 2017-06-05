package scgipp.service.product_management;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kira on 05/06/17.
 */
public class ProductDAO extends DataAccess<Product> {
    /*
     * Implementacao da classe product data acess object
     *
     * Interfacea o acesso ao banco de dados
     */

    public ProductDAO(){
        /*
         * Metodo construtor
         */
        super();
    }

    @Override
    public Integer add(Product newProduct)
            throws ExceptionInInitializerError{
        /*
         * Metodo para adicionar novo cadastro de produto
         *
         * @args:
         * newProdutct: e o produto a ser adiconado
         *
         * @returns:
         * devolve o id gerado para o novo produtp
         */

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

        /*
         * Metodo para remocao de cadastro de produto
         *
         * Remove cadastro de determinado produto baseado no Id desta
         *
         * @args:
         * removeId: id do produto a ser removido
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Product prodAlvo = session.get(Product.class, removeId);
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
        /*
         * Metodo para remocao de cadastro, com base no nome do produto alvo
         *
         * @args:
         * removeNome: nome do referido produto
         */

        Integer idAlvo = null;

        for (Product prodAlvo : list()) {
            if (prodAlvo.getName().equals(removeNome)) {
                idAlvo = prodAlvo.getId();
                break;
            }
        }
        remove(idAlvo);
    }

    @Override
    public Product get(Integer prodId)
            throws ExceptionInInitializerError {
        /*
         * Metodo para buscar produto com base no Id
         *
         * @args:
         * prodId: id do produto alvo
         *
         * @returns:
         * prodAlvo: retorna o produto
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Product prodAlvo = null;
        try {
            tx = session.beginTransaction();
            prodAlvo = session.get(Product.class, prodId);
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

    public Product get(String nomeAlvo)
            throws ExceptionInInitializerError {
        /*
         * Metodo para buscar produto pelo nome
         *
         * @args:
         * nomeAlvo: nome do produto buscado
         *
         * @returns:
         * prodAlvo: O objeto buscado
         */

        Product prodAlvo = null;
        List prodCadastrado = list();
        for (Object objIterator : prodCadastrado) {
            if (((Product)objIterator).getName().equals(nomeAlvo)) {
                prodAlvo = ((Product)objIterator);
                break;
            }
        }
        return prodAlvo;
    }

    @Override
    public Product load(Integer idAlvo)
            throws ExceptionInInitializerError {
        /*
         * Metodo que devolve proxy do produto pelo id
         *
         * @args:
         * idAlvo: o id do produto buscado
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Product prodAlvo = null;
        try  {
            tx = session.beginTransaction();
            prodAlvo = session.load(Product.class, idAlvo);
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
    public void update(Product prodAlvo)
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

    @Override
    public List<Product> list()
            throws ExceptionInInitializerError {
        /*
         * Metodo para listar todos os produtos cadastrados
         *
         * @returns:
         * todosProd: Lista contendo todos os produtos cadastrados
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List todosProd= new ArrayList();
        try{
            tx = session.beginTransaction();
            todosProd = session.createQuery("FROM Product").list();
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
