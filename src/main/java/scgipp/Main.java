package scgipp;

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.data.hibernate.DBConnection;
import scgipp.ui.scenarios.LoginScenario;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;

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

        DBConnection.initialize();

        Scenario loginScenario = new LoginScenario();
        Spawner.newWindow(loginScenario, null);

    }

}
