package scgipp.service.user_management;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.jetbrains.annotations.Contract;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User {

    @Id @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String login;

    private String password;

    @Embedded
    public Permissions permissions;

    protected User() {
        System.out.println("@2211");
    }

    protected User(Permissions.UserType userType) {
        System.out.println("@22");
        permissions = new Permissions(userType);
    }

    public User(String login, String password) {
        this();
        setLogin(login);
        setPassword(password);
    }

    protected User(String login, String password, Permissions.UserType userType) {
        this(userType);
        System.out.println("@232");
        setLogin(login);
        setPassword(password);
    }

    void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public String toString() {
        return "id = " + id + ", login = " + login + ";\n";
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty loginProperty() {
        return new SimpleStringProperty(login);
    }

    public SimpleStringProperty passwordProperty() {
        return new SimpleStringProperty(password);
    }

}

