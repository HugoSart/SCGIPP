package scgipp.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.system.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hugo__<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:29<br/>
 * <br/>
 * Esta classe é responsável por modificar o banco de dados que está tendo sua comunicação a partir do objeto
 * do tipo HibernateUtil especificado na instanciação desta classe.
 *
 */
public class DBManager {

    private HibernateUtil dbConnection;

    public HibernateUtil connection() {
        return dbConnection;
    }

    public DBManager(HibernateUtil dbConnection) {
        this.dbConnection = dbConnection;
    }

    // TODO: Terminar classe de log de atividades e codificar aqui

    /**
     * Método que adiciona uma entidade na tabela do banco de dados correspondente.
     * @param baseEntity Entidade a ser persistida no banco de dados
     * @return Id da entidade persistida
     */
    public Integer add(final BaseEntity baseEntity) {

        Session session = null;

        Transaction transaction = null;
        Integer id = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            id = (Integer)session.save(baseEntity);
            transaction.commit();
        } catch (HibernateException e) {
            standardExceptionCatch(e, transaction);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        baseEntity.setId(id);
        return id;
    }

    /**
     * Método que remove a entidade correspondende na tabela do banco de dados.
     * @param baseEntity a entidade a ser removida
     */
    public void remove(final BaseEntity baseEntity) {

        Transaction transaction = null;
        Session session = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            baseEntity.state = BaseEntity.DELETED;
            session.update(baseEntity);
            transaction.commit();
        } catch (HibernateException e) {
            standardExceptionCatch(e, transaction);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    /**
     * Método que atualiza uma entidade já persistida no banco de dados correspondente.
     * @param object objeto a ser atualizado
     */
    public void update(final BaseEntity object) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            standardExceptionCatch(e, transaction);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    /**
     * Método que recupera uma entidade persistida no banco de dados correspondente.
     * @param clazz tipo da entidade a ser recuperada
     * @param id id da entidade a ser recuperada
     * @return O objeto correspondente a entidade recuperada
     */
    public <T extends BaseEntity> T get(final Class<T> clazz, final Integer id) {

        Session session = null;
        Transaction transaction = null;
        T object = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            object = session.get(clazz, id);
            transaction.commit();
        } catch (HibernateException e) {
            standardExceptionCatch(e, transaction);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return object;

    }

    /**
     * Método para recuperar todas as entidades de uma determiada tablea no banco de dados correspondente.
     * @param clazz classe das entidades a serem recuperadas
     * @param <> tipo das entidades a serem recuperadas
     * @return lista de objetos correspondente a todas as entidades recuperadas
     */
    public <T extends BaseEntity> List<T> list(final Class<T> clazz) {

        Session session = null;

        Transaction transaction = null;
        List<T> objects = new ArrayList<>();

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            objects = session.createCriteria(clazz).list();
            transaction.commit();
        } catch (HibernateException e) {
            standardExceptionCatch(e, transaction);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return objects;

    }

    private void standardExceptionCatch(HibernateException e, Transaction transaction) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
        Log.show("EXCEPTION", e.getClass().getSimpleName(), e.getCause().getMessage());
    }
}
