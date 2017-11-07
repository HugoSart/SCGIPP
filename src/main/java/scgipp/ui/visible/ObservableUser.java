package scgipp.ui.visible;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.service.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 06/11/2017<br/>
 * Time: 20:54<br/>
 */
public class ObservableUser {

    private User user;

    public ObservableUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(user.getId());
    }

    public StringProperty loginProperty() {
        return new SimpleStringProperty(user.getLogin());
    }

    public static List<ObservableUser> userListTAsObservableUserList(List<User> list) {

        List<ObservableUser> observableUsers = new ArrayList<>();
        for (User user : list)
            observableUsers.add(new ObservableUser(user));
        return observableUsers;

    }

}
