package scgipp.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import scgipp.service.entity.User;

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

    HibernateUtil connection() {
        return dbConnection;
    }

    public DBManager(HibernateUtil dbConnection) {
        this.dbConnection = dbConnection;
    }

    // TODO: Terminar classe de log de atividades e codificar aqui

    /**
     * Método que adiciona uma entidade na tabela do banco de dados correspondente.
     * @param entity Entidade a ser persistida no banco de dados
     * @return Id da entidade persistida
     */
    public Integer add(final Entity entity) {

        Session session = null;

        Transaction transaction = null;
        Integer id = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            id = (Integer)session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        entity.setId(id);
        return id;
    }

    /**
     * Método que remove a entidade correspondende na tabela do banco de dados.
     * @param id id da entidade a ser removida
     */
    public void remove(final Integer id) {

        Transaction transaction = null;

        try (Session session = dbConnection.openSession()) {
            transaction = session.beginTransaction();
            Entity entity = session.get(User.class, id);
            session.delete(entity);
            transaction.commit();
            session.close();
        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }

    /**
     * Método que atualiza uma entidade já persistida no banco de dados correspondente.
     * @param object objeto a ser atualizado
     */
    public void update(final Entity object) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * Método que recupera uma entidade persistida no banco de dados correspondente.
     * @param clazz tipo da entidade a ser recuperada
     * @param id id da entidade a ser recuperada
     * @return O objeto correspondente a entidade recuperada
     */
    public <T extends Entity> T get(final Class<T> clazz, final Integer id) {

        Session session = null;
        Transaction transaction = null;
        T object = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            object = session.get(clazz, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return object;

    }

    /**
     * Método para recuperar todas as entidades de uma determiada tablea no banco de dados correspondente.
     * @param clazz classe das entidades a serem recuperadas
     * @param <> tipo das entidades a serem recuperadas
     * @return lista de objetos correspondente a todas as entidades recuperadas
     */
    public <T extends Entity> List<T> list(final Class<T> clazz) {

        Transaction transaction = null;
        List<T> objects = new ArrayList<>();

        try (Session session = dbConnection.openSession()) {
            transaction = session.beginTransaction();
            objects = session.createCriteria(clazz).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return objects;

    }

}
