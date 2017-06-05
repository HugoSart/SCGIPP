package scgipp;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.customer_management.Customer;
import scgipp.service.customer_management.CustomerManager;
import scgipp.service.product_management.ProductManager;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.transportadora_management.TransportadoraManager;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.*;
import scgipp.service.user_management.UserManager;
import scgipp.ui.login.LoginUIManager;
import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Teste extends Application {

    public static void main(String[] args) {
        //addTestData();

        /*
         * Area para teste de codigo
         * Apenas a instanciacao de algumas classe para ver se ta funfando
         */

        UserManager criar = new UserManager();
        criar.register("admin", "admin123", Permissions.UserType.ADM);
        TransportadoraManager teste = new TransportadoraManager();
        teste.register("10683417", "ROLEZAO",  LocalDate.now(), "99999998", null);
        teste.uthenticate("ROLEZAO");
        ProductManager prod = new ProductManager();
        prod.register(0, 10, "Isca",
                      "ISCA PARA FISHING MIN. LVL 10", 10.00);
        prod.uthenticate("Isca");

        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginUIManager loginFXMLManager = new LoginUIManager();
        Stage stage = loginFXMLManager.newStage();
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toString());
        stage.show();

    }

    public static void addTestData() {
        UserManager userManager = new UserManager();
        System.out.println(userManager.register("admin", "admin", Permissions.UserType.ADM).getPermissions().check(Permissions.Permission.LOGIN));
    }

}
