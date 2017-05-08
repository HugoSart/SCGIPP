package scgipp;

import scgipp.service.user_management.User;

import javax.naming.AuthenticationException;

import static scgipp.service.user_management.Permissions.Permission.*;

public class Teste {

    public static void main(String args[]) {
        User user;
        try {
            user = User.newInstance("aa", "bb");
            user.permissions.add(LOGIN);
            user.permissions.add(USER_ADD);
            user.permissions.add(USER_REMOVE);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }

}
