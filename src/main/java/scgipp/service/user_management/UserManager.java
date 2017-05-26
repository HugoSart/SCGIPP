package scgipp.service.user_management;

import scgipp.data.encryption.Encryptor;
import scgipp.system.log.Log;

import java.util.List;

public class UserManager {

    private UserDAO userDAO = new UserDAO();

    public UserManager() {}

    public void register(String login, String password) {
        try {
            userDAO.add(new User(login, Encryptor.encrypt(password)));
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" successufly registred.");
    }

    public void register(String login, String password, Permissions.UserType userType) {
        try {
            userDAO.add(new User(login, Encryptor.encrypt(password), userType));
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" successufly registred.");
    }

    public void remove(String login) {
        try {
            userDAO.remove(login);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" removed.");
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
                Log.show(Log.Type.INFO, "Authentication Successfuly Done!");
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
