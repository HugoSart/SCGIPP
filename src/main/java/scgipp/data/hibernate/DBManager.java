package scgipp.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Where;
import org.reflections.Reflections;
import scgipp.system.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static scgipp.data.hibernate.BaseEntity.DELETED;
import static scgipp.data.hibernate.BaseEntity.NORMAL;

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
    public <T extends Serializable> T add(final BaseEntity<T> baseEntity) {

        Session session = null;

        Transaction transaction = null;
        T id = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            id = (T)session.save(baseEntity);
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
            baseEntity.state = DELETED;
            session.update                                                                                                                                  (baseEntity);
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
    public <T extends BaseEntity> T get(final Class<T> clazz, final Object id) {

        Session session = null;
        Transaction transaction = null;
        T object = null;

        try {
            session = dbConnection.openSession();
            transaction = session.beginTransaction();
            if (id instanceof Integer)
                object = session.get(clazz, (Integer)id);
            else if (id instanceof String)
                object = session.get(clazz, (String)id);
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
    private <T extends BaseEntity> List<T> listFiltered(final Class<T> clazz, int state) {

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

        List<T> ret = new ArrayList<>();
        for (T object : objects)
            if (object.state == state)
                ret.add(object);

        return ret;

    }

    public <T extends BaseEntity> List<T> list(final Class<T> clazz) {
        return listFiltered(clazz, NORMAL);
    }

    public <T extends BaseEntity> List<T> recovery(final Class<T> clazz) {
        return listFiltered(clazz, DELETED);
    }

    public List<BaseEntity> recoveryAll() {

        Reflections reflections = new Reflections("scgipp.service.entities");
        Set<Class<? extends BaseEntity>> classes = reflections.getSubTypesOf(BaseEntity.class);

        List<BaseEntity> entities = new ArrayList<>();
        for (Class<? extends BaseEntity> aClass : classes)
            entities.addAll(recovery(aClass));

        return entities;

    }

    private void standardExceptionCatch(HibernateException e, Transaction transaction) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
        Log.show("EXCEPTION", e.getClass().getSimpleName(), e.getCause().getMessage());
    }
}
