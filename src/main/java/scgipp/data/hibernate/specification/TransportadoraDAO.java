package scgipp.data.hibernate.specification;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.transportadora_management.Transportadora;

import javax.xml.crypto.dsig.TransformService;

/**
 * Created by kira on 05/06/17.
 */
public class TransportadoraDAO extends DataAccess<Transportadora> {
        /*
         * Classe controladora para transportadora
         *
         * Utilizada para acessar as operações sobre transportadora
         */

    public TransportadoraDAO(){
        /*
         * Metodo construtor
         */
        super();
    }

    @Override
    public Integer add(Transportadora novaTransp)
        throws ExceptionInInitializerError{
        /*
         * Metodo para adicionar novo cadastro de transportadora
         *
         * @args:
         * novaTransp: a transportadora a ser adicionada
         *
         * @returns:
         * devolve o id gerado para a nova transportadora
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer transpId = null;

        try{
            tx = session.beginTransaction();
            transpId = (Integer)session.save(novaTransp);
            tx.commit();
        }
        catch (HibernateException error){
            if (tx == null) tx.rollback();
            error.printStackTrace();
        }
        finally{
            session.close();
        }

        return transpId;
    }

    @Override
    public void remove(Integer removeId)
        throws ExceptionInInitializerError{

        /*
         * Metodo para remocao de cadastro da transportadora
         *
         * Remove cadastro de determinada transportador baseado no Id desta
         *
         * @args:
         * removeId: id da transportadora a ser removida
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Transportadora transpAlvo = session.get(Transportadora.class, removeId);
            session.delete(transpAlvo);
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
         * Metodo para remocao de cadastro, com base no nome da transportadora alvo
         *
         * @args:
         * removeNome: nome da referida transportadora
         */

        Integer idAlvo = null;

        for (Transportadora transpAlvo : list()) {
            if (transpAlvo.getName().equals(removeNome)) {
                idAlvo = transpAlvo.getId();
                break;
            }
        }
        remove(idAlvo);
    }

    @Override
    public Transportadora get(Integer transpId)
        throws ExceptionInInitializerError {
        /*
         * Metodo para buscar transportadora com base no Id
         *
         * @args:
         * transpId: id da transportadora alvo
         *
         * @returns:
         * transpAlvo: retorna a transportadora
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Transportadora transpAlvo = null;
        try {
            tx = session.beginTransaction();
            transpAlvo = session.get(Transportadora.class, transpId);
            tx.commit();
        }
        catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally {
            session.close();
        }

        return transpAlvo;
    }

    public Transportadora get(String nomeAlvo)
        throws ExceptionInInitializerError {
        /*
         * Metodo para buscar transportadora pelo nome
         *
         * @args:
         * nomeAlvo: nome da transportadora buscada
         *
         * @returns:
         * transpAlvo: A transportadora buscada
         */

        Transportadora transpAlvo = null;
        List transpCadastrada = list();
        for (Object objIterator : transpCadastrada) {
            if (((Transportadora)objIterator).getName().equals(nomeAlvo)) {
                transpAlvo = ((Transportadora)objIterator);
                break;
            }
        }
        return transpAlvo;
    }

    @Override
    public Transportadora load(Integer idAlvo)
        throws ExceptionInInitializerError {
        /*
         * Metodo que devolve proxy da transportadora pelo id
         *
         * @args:
         * idAlvo: o id da tranportadora buscada
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Transportadora transpAlvo = null;
        try  {
            tx = session.beginTransaction();
            transpAlvo = session.load(Transportadora.class, idAlvo);
            tx.commit();
        }
        catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        }
        finally {
            session.close();
        }
        return transpAlvo;
    }

    @Override
    public void update(Transportadora transpAlvo)
        throws ExceptionInInitializerError {
        /*
         * Metodo para atualizar transportadora
         *
         * @args:
         * transpAlvo: a transportadora a ser atualizada
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(transpAlvo);
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
    public List<Transportadora> list()
        throws ExceptionInInitializerError {
        /*
         * Metodo para listar todas as transportadoras cadastradas
         *
         * @returns:
         * todasTransp: Lista contendo todas as tranposrtadoras cadastradas
         */

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List todasTransp = new ArrayList();
        try{
            tx = session.beginTransaction();
            todasTransp = session.createQuery("FROM Transportadora").list();
            tx.commit();
        } catch (HibernateException error) {
            if (tx != null) tx.rollback();
            error.printStackTrace();
        } finally {
            session.close();
        }

        return todasTransp;
    }


}
