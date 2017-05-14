package scgipp;

import scgipp.service.user_management.UserManager;

public class Teste {

    public static void main(String args[]) {

        UserManager userManager = new UserManager();
        userManager.register("hsart", "123");
        userManager.authenticate("hsart", "123");
        userManager.remove("hsart");

    }

}
