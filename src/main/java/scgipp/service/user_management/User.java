package scgipp.service.user_management;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.Embedded;

/**
 * Created by hsart on 13/05/17.
 */

@javax.persistence.Entity
public class User extends Entity {

    @Column(unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Embedded
    public Permissions permissions;

    protected User() {}

    protected User(Permissions.UserType userType) {
        permissions = new Permissions(userType);
    }

    public User(String login, String password) {
        this();
        setLogin(login);
        setPassword(password);
    }

    public User(String login, String password, Permissions.UserType userType) {
        this(userType);
        setLogin(login);
        setPassword(password);
    }

    public void setId(Integer id) {
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

    public void setPassword(String password) {
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

