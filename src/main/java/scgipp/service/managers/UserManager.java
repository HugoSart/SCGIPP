package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.encryption.Encryptor;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.User;
import scgipp.system.log.Log;

import javax.persistence.Entity;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 18:19<br/>
 */
public class UserManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addUser(@NotNull User user) {
        Integer id = dbManager.add(user);
        if (user.getId() != null)
            Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> added to scgipp_db.");
        return id;
    }

    public static void updateUser(@NotNull User user) {
        dbManager.update(user);
        Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been updated in scgipp_db.");
    }

    public static User authenticate(@NotNull String login, @NotNull String password) {

        password = Encryptor.encrypt(password);

        for (User user : dbManager.list(User.class)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been authenticated in scgipp_db.");
                return user;
            }
        }

        return null;

    }

}
