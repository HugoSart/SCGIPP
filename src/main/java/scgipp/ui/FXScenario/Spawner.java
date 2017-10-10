package scgipp.ui.FXScenario;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public static void newFeedbackWindow(Stage stage, @NotNull FeedbackScenario scenario, int requestCode, @Nullable Scenario parent, @NotNull FeedbackScenario.FeedbackListener listener) {
        scenario.listener = listener;
        scenario.parent = parent;
        scenario.requestCode = requestCode;
        scenario.stage = stage;
        scenario.create();
        scenario.stage.show();
    }

    public static void newFeedbackWindow(@NotNull FeedbackScenario scenario, int requestCode, @NotNull Scenario parent, @NotNull FeedbackScenario.FeedbackListener listener) {
        newFeedbackWindow(null, scenario, requestCode, parent, listener);
    }

    public static void newFeedbackWindow(@NotNull FeedbackScenario scenario, int requestCode, @NotNull FeedbackScenario.FeedbackListener listener) {
        newFeedbackWindow(null, scenario, requestCode, null, listener);
    }

    public static void newFragment(@NotNull Fragment fragment, Scenario parent, @NotNull Pane rootPane) {
        fragment.parent = parent;
        rootPane.getChildren().clear();
        fragment.rootPane = rootPane;
        fragment.create();
    }

}
