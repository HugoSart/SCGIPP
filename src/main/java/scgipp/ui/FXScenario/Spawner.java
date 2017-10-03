package scgipp.ui.FXScenario;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 20:59<br/>
 */
public class Spawner {

    public static void newWindow(Stage stage, Scenario scenario, Scenario parent) {

        scenario.parent = parent;

        scenario.stage = stage;
        scenario.create();
        scenario.stage.show();

    }

    public static void newWindow(@NotNull Scenario scenario, @Nullable Scenario parent) {
        newWindow(null, scenario, parent);
    }

    public static void newWindow(@NotNull FeedbackScenario scenario, @NotNull int requestCode, @NotNull Scenario parent) {
        scenario.requestCode = requestCode;
        newWindow(scenario, parent);
    }

}
