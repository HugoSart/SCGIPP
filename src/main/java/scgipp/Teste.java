package scgipp;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.service.customer_management.CustomerManager;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserDAO;
import scgipp.service.user_management.UserManager;
import scgipp.ui.login.LoginUIManager;

import java.awt.*;
import java.util.Calendar;

public class Teste extends Application {

    public static void main(String[] args) {
        addTestData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginUIManager loginFXMLManager = new LoginUIManager();
        Stage stage = loginFXMLManager.newStage();
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.show();

    }

    public static void addTestData() {
        UserManager userManager = new UserManager();
        System.out.println(userManager.register("admin", "admin", Permissions.UserType.ADM).getPermissions().check(Permissions.Permission.LOGIN));
        CustomerManager customerManager = new CustomerManager();
        customerManager.register(new Customer(Customer.Type.PHYSICAL, "José Serra", "114.032.079-33", Calendar.getInstance()));
        customerManager.register(new Customer(Customer.Type.PHYSICAL, "Luís Serra", "114.032.079-22", Calendar.getInstance()));
        customerManager.register(new Customer(Customer.Type.PHYSICAL, "Eu Serra", "114.032.079-11", Calendar.getInstance()));

    }

}
