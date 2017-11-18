<<<<<<< HEAD
package scgipp.service.managers;

import javassist.NotFoundException;
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

    private static UserManager instance = null;

    private DBManager dbManager = DBConnection.manager();

    public static UserManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new UserManager();
        return instance;
    }

    public Integer addUser(@NotNull User user) {
        Integer id = dbManager.add(user);
        if (user.getId() != null)
            Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> added to scgipp_db.");
        return id;
    }

    public void updateUser(@NotNull User user) {
        dbManager.update(user);
        Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been updated in scgipp_db.");
    }

    public User authenticate(@NotNull String login, @NotNull String password) throws NotFoundException {

        password = Encryptor.encrypt(password);

        for (User user : dbManager.list(User.class)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been authenticated in scgipp_db.");
                return user;
            }
        }

        throw new NotFoundException("Failed to authenticate User { login = " + login + ", password = " + password + " }.");

    }

    public void delete(@NotNull User user) {
        dbManager.remove(user);
    }

    public List<User> getAll() {
        return DBConnection.manager().list(User.class);
    }

}
=======
package scgipp.service.managers;

import javassist.NotFoundException;
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

    private static UserManager instance = null;

    private DBManager dbManager = DBConnection.manager();

    public static UserManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new UserManager();
        return instance;
    }

    public Integer addUser(@NotNull User user) {
        Integer id = dbManager.add(user);
        if (user.getId() != null)
            Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> added to scgipp_db.");
        return id;
    }

    public void updateUser(@NotNull User user) {
        dbManager.update(user);
        Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been updated in scgipp_db.");
    }

    public User authenticate(@NotNull String login, @NotNull String password) throws NotFoundException {

        password = Encryptor.encrypt(password);

        for (User user : dbManager.list(User.class)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                Log.show("DATABASE", "User", "User <id = " + user.getId() + ", login = " + user.getLogin() + "> has been authenticated in scgipp_db.");
                return user;
            }
        }

        throw new NotFoundException("Failed to authenticate User { login = " + login + ", password = " + password + " }.");

    }

    public void delete(@NotNull User user) {
        dbManager.remove(user);
    }

    public List<User> getAll() {
        return DBConnection.manager().list(User.class);
    }

}
>>>>>>> [C]ObservableCustomer
