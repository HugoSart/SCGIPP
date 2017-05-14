package scgipp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.UserManager;

public class Teste extends Application {

    public static void main(String[] args) {
        addTestData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login_screen.fxml"));

        Parent root = loader.load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("SCGIPP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void addTestData() {
        UserManager userManager = new UserManager();
        userManager.register("admin", "admin", Permissions.UserType.ADM);
        userManager.register("hsart", "123", Permissions.UserType.SELLER);
    }

}
