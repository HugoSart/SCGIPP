package scgipp;

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.ui.controller.LoginActivity;
import scgipp.ui.framework.Spawner;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 21:15<br/>
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LoginActivity loginActivity = new LoginActivity();
        Spawner.newWindow(primaryStage, loginActivity, null);

    }
}
