package scgipp.ui.FXScenario;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:00<br/>
 */
public abstract class Scenario {

    Scenario parent;
    Map<String, Object> extraInformation = new HashMap<>();

    String fxmlPath;

    Stage stage = null;

    public Scenario(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }



    // ============ LIFE CYCLE METHODS ================= //

    final void create() {

        onCreate();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
        fxmlLoader.setController(this);

        Scene scene;

        try {



            // Config Scene
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            configScene(scene);

            // Config Stage
            if (stage == null) stage = new Stage();
            stage.setScene(scene);
            configStage(stage);

            ready();

        } catch (IOException e) {
            System.err.println("Failed to create " + getClass().getSimpleName() + " scenario.");
            e.printStackTrace();
        }

    }

    final void configScene(Scene scene) {
        onConfigScene(scene);
    }

    final void configStage(Stage stage) {
        onConfigStage(stage);
    }

    final void ready() {
        onReady();
    }

    final void destroy() {
        onDestroy();
        stage.close();
    }



    // ============= CUSTOMIZATION CYCLE METHODS ============= //

    protected void onCreate() {}

    protected void onConfigScene(Scene scene) {}

    protected void onConfigStage(Stage stage) {}

    protected void onReady() {}

    protected void onDestroy() {}



    // ================ OTHER METHODS ======================//

    public void finish() {
        destroy();
    }

    public void putExtra(String id, Object object) {
        extraInformation.put(id, object);
    }

    public Object getExtra(String id) {
        return extraInformation.get(id);
    }



    // ============ SETTERS AND GETTERS ================= //

    public Scenario getParent() {
        return parent;
    }

    public Stage getStage() {
        return stage;
    }

}
