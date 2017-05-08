package scgipp.service.user_management;

import java.util.ArrayList;
import java.util.List;

import static scgipp.service.user_management.Permissions.Permission.*;

public class Permissions {

    private List<Permission> permissions = new ArrayList<Permission>();

    public enum Permission {
        LOGIN,
        USER_ADD, USER_REMOVE, USER_UPDATE
    }

    public enum UserType {
        ADM, SELLER
    }

    Permissions() {}

    Permissions(UserType ut) {

        switch (ut) {
            case ADM:
                add(LOGIN);
                add(USER_ADD);
                add(USER_REMOVE);
                add(USER_UPDATE);
                break;
            case SELLER:

                break;
            default:

        }

    }

    public boolean add(Permission permission) {

        if (permissions.contains(permission))
            return false;
        else
            permissions.add(permission);
        return true;

    }

    public boolean remove(Permission permission) {

        if (!permissions.contains(permission))
            return false;
        else
            permissions.remove(permission);
        return true;

    }

    public boolean check(Permission permission) {
        return permissions.contains(permission);
    }

    public void print() {

        for (Permission p : permissions) {
            System.out.print("" + p.name() + " ");
        }

        System.out.println();

    }

}

