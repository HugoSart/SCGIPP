package scgipp.service.user_management;

import scgipp.data.encryption.Encryptor;
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
        Log.show(Log.Type.INFO, "User \"" + login + "\" successufly registred.");

        return user;
    }

    public User register(String login, String password, Permissions.UserType userType) {
        User user = new User(login, Encryptor.encrypt(password), userType);
        try {
            userDAO.add(user);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" - \"" + password + "\" successufly registred.");
        System.out.println(Encryptor.encrypt(password));

        return user;
    }

    public void remove(String login) {
        try {
            userDAO.remove(login);
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        Log.show(Log.Type.INFO, "User \"" + login + "\" removed.");
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

            System.out.println("up: " + user.getPassword() + "\n dp: " + password);

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
