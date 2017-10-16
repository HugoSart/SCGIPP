package scgipp.service.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.data.encryption.Encryptor;
import scgipp.data.hibernate.Entity;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.entities.superclass.Person;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
        this(Permissions.UserType.TEST);
        setLogin(login);
        setPassword(password);
    }

    public User(String login, String password, Permissions.UserType userType) {
        this(userType);
        setLogin(login);
        setPassword(password);
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = Encryptor.encrypt(password);
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
        return "User[id=" + getId() + ", login=" + login + ", password=" + password + "]\n";
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public StringProperty loginProperty() {
        return new SimpleStringProperty(login);
    }

}

