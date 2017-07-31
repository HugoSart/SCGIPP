package scgipp;

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.service.Address;
import scgipp.service.transportadora_management.TransportadoraManager;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.LoginUIManager;

import java.time.LocalDate;

public class Teste extends Application {

    public static void main(String[] args) {
        addTestData();
        UserManager criar = new UserManager();
        criar.register("admin", "admin", Permissions.UserType.ADM);
        TransportadoraManager transpM = new TransportadoraManager();
        Address ad = new Address("Maringa", "Braisl" ,"Parana", null, null, null,"null");
        transpM.register("123312", "ROLE", LocalDate.now(), "aeuhaeu",ad );
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginUIManager loginFXMLManager = new LoginUIManager();
        loginFXMLManager.newStage().show();

    }

    public static void addTestData() {
        UserManager userManager = new UserManager();
        userManager.register("admin", "admin", Permissions.UserType.ADM);
    }

}
