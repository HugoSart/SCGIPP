package scgipp.service.user_management;

import org.jetbrains.annotations.Contract;

import javax.naming.AuthenticationException;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    //<editor-fold desc="Atributes{...}">

    @Id
    @GeneratedValue private Integer id;

    @Column(nullable = false, unique = true) private String login;
    @Column(nullable = false)                private String password;

    //@Transient public final Permissions permissions;

    //</editor-fold>

    // <editor-fold desc="Objects References{...}">
    //@Transient private final UserManager userManager;
    // </editor-fold>

    public User() {

    }

    public User(Integer id, String login) {
        this();
        this.id = id;
        this.login = login;

    }

    public User(String login, String password) {
        this();
        this.login = login;
        this.password = password;
    }

    // </editor-fold>

    // <editor-fold desc="Setters and Getters{...}">

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // </editor-fold>

    @Contract(pure = true)
    public static User newInstance(String login, String password) throws AuthenticationException {
        return null;
    }

    public String toString() {
        return "id = " + id + ", login = " + login + ";\n";
    }

}

