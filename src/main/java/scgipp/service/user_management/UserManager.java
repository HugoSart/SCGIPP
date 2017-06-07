package scgipp.service.user_management;

import scgipp.data.encryption.Encryptor;
import scgipp.data.hibernate.specification.UserDAO;
import scgipp.system.log.Log;

import java.util.List;

public class UserManager {

    private UserDAO userDAO = new UserDAO();

    public UserManager() {}

    public User register(String login, String password) {
        User user = new User(login, Encryptor.encrypt(password));
        try {
            userDAO.add(user);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" added to database.");

        return user;
    }

    public User register(String login, String password, Permissions.UserType userType) {
        User user = new User(login, Encryptor.encrypt(password), userType);
        try {
            userDAO.add(user);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + " added to database.");

        return user;
    }

    public void remove(String login) {
        try {
            userDAO.remove(login);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" removed from database.");
    }

    public void remove(User user) {
        remove(user.getLogin());
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public User authenticate(String login, String password) {

        User user;

        try {
            user = userDAO.get(login);

            if (user == null) {
                Log.show(Log.Type.INFO,"Authentication Failed", "User not found.");
                return null;
            }

            if (user.getPassword().equals(Encryptor.encrypt(password))) {
                Log.show(Log.Type.INFO, "User " + login + " authenticated.");
                return user;
            } else Log.show(Log.Type.INFO, "Authentication Failed", "User does not match with password.");

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAll() {
        return userDAO.list();
    }

}
