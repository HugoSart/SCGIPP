package scgipp.service.user_management;

import org.jetbrains.annotations.Contract;
import javax.naming.AuthenticationException;

public class User {

    Long id;
    String login;

    public final Permissions permissions;

    private User(String login) {
        permissions = new Permissions();
        this.login = login;

    }

    @Contract(pure = true)
    public static User newInstance(String login, String password) throws AuthenticationException {
        User user = new User(login);
        return user;
    }


}

