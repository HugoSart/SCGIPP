package scgipp.service.managers;

import scgipp.data.encryption.Encryptor;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.User;

import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 18:19<br/>
 */
public class UserManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addUser(User user) {
        return dbManager.add(user);
    }

    public static void updateUser(User user) {
        dbManager.update(user);
    }

    public static User authenticate(String login, String password) {

        password = Encryptor.encrypt(password);

        for (User user : dbManager.list(User.class)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return user;
        }

        return null;

    }

}
