package person_management.user_management;

import person_management.Person;

public class User extends Person {

    private String login;

    public User(int id, Type type) {
        super(id, type);
    }

}
