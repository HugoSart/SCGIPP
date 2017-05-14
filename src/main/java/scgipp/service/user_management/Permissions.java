package scgipp.service.user_management;

import org.hibernate.collection.internal.PersistentSet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static scgipp.service.user_management.Permissions.Permission.*;

@Embeddable
public class Permissions {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    private Set<Permission> permissions = new HashSet<Permission>();

    public enum Permission {
        LOGIN,
        USER_ADD, USER_REMOVE, USER_UPDATE
    }

    public enum UserType {
        ADM, SELLER
    }

    protected Permissions() {}

    protected Permissions(UserType ut) {

        switch (ut) {
            case ADM:
                add(LOGIN);
                add(USER_ADD);
                add(USER_REMOVE);
                add(USER_UPDATE);
                break;
            case SELLER:
                add(LOGIN);
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

