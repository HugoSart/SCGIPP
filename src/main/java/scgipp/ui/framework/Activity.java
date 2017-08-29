package scgipp.ui.framework;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:00<br/>
 */
public abstract class Activity {

    String id;

    Activity parent;
    List<Activity> chields = new ArrayList<>();
    Map<String, Object> extraInformations = new HashMap<>();

    String fxmlPath;

    public Activity(Activity parent, String fxmlPath) {
        this.parent = parent;
        this.fxmlPath = fxmlPath;
    }


    // ============ LIFE CYCLE METHODS ================= //

    final void create(Stage stage) {
        onCreate(stage);
        configStage(stage);
    }

    final void configStage(Stage stage) {
        onConfigStage(stage);
        configScene(stage.getScene());
    }

    final void configScene(Scene scene) {
        onConfigScene(scene);
    }



    // ============= CUSTOMIZATION CYCLE METHODS ============= //

    public void onCreate(Stage stage) {}

    public void onConfigScene(Scene scene) {}

    public void onConfigStage(Stage stage) {}



    // ============ SETTERS AND GETTERS ================= //

    public String getId() {
        return id;
    }

    public Activity getParent() {
        return parent;
    }

}
