package scgipp.ui.framework;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.*;

import static scgipp.ui.framework.Activity.Type.FEEDBACK;
import static scgipp.ui.framework.Activity.Type.NORMAL;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:00<br/>
 */
public abstract class Activity {

    String id;

    Activity parent;
    Map<String, Activity> children = new HashMap<>();
    Map<String, Object> extraInformations = new HashMap<>();

    String fxmlPath;

    Stage stage;

    public Activity(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    enum Type {
        NORMAL, FEEDBACK
    }


    // ============ LIFE CYCLE METHODS ================= //

    final void create(Stage stage) {
        this.stage = stage;
        onCreate(stage);
        configStage(stage);
    }

    final void configStage(Stage stage) {
        onConfigStage(stage);
        configScene(stage.getScene());
    }

    final void configScene(Scene scene) {
        onConfigScene(scene);
        ready();
    }

    final void ready() {
        onReady();
    }

    final void destroy() {
        onDestroy();
        stage.close();
        if (parent != null) parent.children.remove(id);
    }


    // ============= CUSTOMIZATION CYCLE METHODS ============= //

    public void onCreate(Stage stage) {}

    public void onConfigScene(Scene scene) {}

    public void onConfigStage(Stage stage) {}

    public void onReady() {}

    public void onDestroy() {}



    // ============== CUSTOMIZATION OF EPECIAL CALL METHODS ============ //

    public void onFeedback(String id) {

    }


    // ================ OTHER METHODS ======================//

    public void finish() {
        destroy();
    }

    public void putExtra(String id, Object object) {
        extraInformations.put(id, object);
    }

    public Object getExtra(String id) {
        return extraInformations.get(id);
    }

    // ============ SETTERS AND GETTERS ================= //

    public String getId() {
        return id;
    }

    public Activity getParent() {
        return parent;
    }

    public Activity getChildren(String id) {
        return children.get(id);
    }

    public Stage getStage() {
        return stage;
    }

}
