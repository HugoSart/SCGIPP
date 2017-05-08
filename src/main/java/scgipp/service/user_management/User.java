package scgipp.service.user_management;

import org.jetbrains.annotations.Contract;
import javax.naming.AuthenticationException;

public class User {

    Long id;
    String login;

    private final UserController userController;

    public final Permissions permissions;

    User() {
        userController = new UserController();
        permissions = new Permissions();
    }

    User(Long id, String login) {
        this();
        this.id = id;
        this.login = login;

    }

    @Contract(pure = true)
    public static User newInstance(String login, String password) throws AuthenticationException {
        User user = UserController.authenticate(login, password);

        if (user == null) throw new AuthenticationException("Failed to authenticate \"" + login + "\".");

        return user;
    }


}

