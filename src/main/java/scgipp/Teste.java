package scgipp;

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.LoginUIManager;

public class Teste extends Application {

    public static void main(String[] args) {
        //addTestData();
        UserManager criar = new UserManager();
        criar.register("admin", "admin", Permissions.UserType.ADM);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginUIManager loginFXMLManager = new LoginUIManager();
        loginFXMLManager.newStage().show();

    }

    public static void addTestData() {
        UserManager userManager = new UserManager();
        System.out.println(userManager.register("admin", "admin", Permissions.UserType.ADM).getPermissions().check(Permissions.Permission.LOGIN));
    }

}
