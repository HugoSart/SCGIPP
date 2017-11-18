package scgipp.ui.FXScenario;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 08/10/2017<br/>
 * Time: 21:33<br/>
 */
public class Fragment extends Controller {

    Pane rootPane;

    public Fragment(String fxmlPath) {
        super(fxmlPath);
    }



    // ============ LIFE CYCLE METHODS ================= //

    void create() {

        onCreate();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
        fxmlLoader.setController(this);

        try {

            Pane newLoadedPane = fxmlLoader.load();

            onCreateView();

            rootPane.getChildren().add(newLoadedPane);

            ready();

        } catch (IOException e) {
            System.err.println("Failed to create " + getClass().getSimpleName() + " fragment.");
            e.printStackTrace();
        }

    }

    final void ready() {
        onReady();
    }

    final void destroy() {
        onDestroy();
    }



    // ============= CUSTOMIZATION CYCLE METHODS ============= //

    protected void onCreate() {}

    protected void onCreateView() {}

    protected void onReady() {}

    protected void onDestroy() {}

}
