package scgipp.ui.framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 20:59<br/>
 */
public class Spawner {

    public static void newWindow(Stage stage, Activity activity) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(activity.getClass().getClassLoader().getResource(activity.fxmlPath));
            fxmlLoader.setController(activity);

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            activity.create(stage);
            stage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
