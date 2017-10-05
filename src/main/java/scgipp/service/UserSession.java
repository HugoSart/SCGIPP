package scgipp.service;

import scgipp.service.entities.User;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Calendar;

public class UserSession {

    private static UserSession instance;

    private final User user;
    private Calendar calendar;

    private UserSession(User user) {
        this.user = user;
        this.calendar = Calendar.getInstance();
    }

    public static UserSession openSession(User user) throws InstanceAlreadyExistsException {
        if (hasSession())
            throw new InstanceAlreadyExistsException();
        instance = new UserSession(user);
        return instance;
    }

    public static UserSession getSession() {
        return instance;
    }

    public static boolean hasSession() {
        return instance != null;
    }

    public void closeSession() {
        instance = null;
    }

    public User getActiveUser() {
        return user;
    }

}
