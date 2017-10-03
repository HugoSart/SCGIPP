package framework_test; /**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 16:41<br/>
 */

import framework_test.activities.UserScenario;
import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.ui.FXScenario.Spawner;

public class TestMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        UserScenario userActivity = new UserScenario();
        Spawner.newWindow(primaryStage, userActivity, null);

    }
}
