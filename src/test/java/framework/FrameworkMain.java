package framework; /**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 16:41<br/>
 */

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.ui.framework.Spawner;

public class FrameworkMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        UserActivity userActivity = new UserActivity();
        Spawner.newWindow(primaryStage, userActivity, null);

    }
}
