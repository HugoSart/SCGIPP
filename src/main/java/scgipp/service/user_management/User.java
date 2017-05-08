package scgipp.service.user_management;

import org.jetbrains.annotations.Contract;

import javax.naming.AuthenticationException;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Transient private final UserController userController;
    @Transient public  final Permissions permissions;

    public User() {
        userController = new UserController();
        permissions = new Permissions();
    }

    public User(Long id, String login) {
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

    public String toString() {
        return "id = " + id + ", login = " + login + ";";
    }


}

