package scgipp.service.entities.embbeded;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static scgipp.service.entities.embbeded.Permissions.Permission.*;

/**
 * Created by hsart on 13/05/17.
 */
@Embeddable
public class Permissions {

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Permission> permissions = new HashSet<>();

    public enum Permission {
        LOGIN,
        USER_ADD, USER_REMOVE, USER_UPDATE,
        CLIENT_ADD, CLIENT_REMOVE, CLIENT_UPDATE
    }

    public enum UserType {
        ADM, SELLER, TEST
    }

    public Permissions() {}

    public Permissions(UserType ut) {
        switch (ut) {
            case ADM:

                for (Permission p : Permission.values())
                    add(p);

                break;
            case SELLER:
                add(LOGIN);
                add(CLIENT_ADD);
                add(CLIENT_REMOVE);
                add(CLIENT_UPDATE);
                break;
            default:
                add(LOGIN);

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

    public Set<Permission> toSet() {
        return permissions;
    }

}

