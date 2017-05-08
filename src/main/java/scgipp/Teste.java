package scgipp;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.service.internal.StandardSessionFactoryServiceInitiators;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import scgipp.service.user_management.User;

import javax.naming.AuthenticationException;

import static scgipp.service.user_management.Permissions.Permission.*;

public class Teste {

    public static void main(String args[]) {
        User user;
        try {
            user = User.newInstance("aa", "bb");
            user.permissions.add(LOGIN);
            user.permissions.add(USER_ADD);
            user.permissions.add(USER_REMOVE);
            user.permissions.remove(USER_ADD);
            user.permissions.print();

            Configuration config = new Configuration().configure();
            SessionFactory sessionFactory = config.buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.persist(user);

            session.close();


        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }

}
