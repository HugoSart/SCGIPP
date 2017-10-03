/*package scgipp.service.session_management;

import scgipp.service.user_management.Permissions;
import scgipp.service.entity.User;

import javax.security.sasl.AuthenticationException;
import java.util.Calendar;

public class UserSession {

    private static UserSession instance;

    public final User user;

    private Calendar openTime;

    private UserSession() {
        user = null;
    }

    private UserSession(User user) {
        this.user = user;
        openTime = Calendar.getInstance();
    }

    public static UserSession open(String login, String password) throws AuthenticationException {
        UserManager userManager = new UserManager();
        User user = userManager.authenticate(login, password);

        if (user == null) {
            throw new AuthenticationException("Authentication problem. Session init aborted.");
        }

        if (!user.getPermissions().check(Permissions.Permission.LOGIN)) {
            throw new AuthenticationException("Este usuário não tem permissão para entrar.");
        }

        instance = new UserSession(user);

        return instance;

    }

    public static User getUser() {
        return instance.user;
    }

    public static void close() {
        instance = null;
    }

}*/
