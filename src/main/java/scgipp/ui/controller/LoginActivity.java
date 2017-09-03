package scgipp.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.framework.Activity;
import scgipp.ui.framework.WidgetCustomizer;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginActivity extends Activity {

    @FXML private HBox menu;
    @FXML private Button exitButton;
    @FXML private Button enterButton;

    public LoginActivity() {
        super("fxml/login.fxml");
    }

    @Override
    public void onConfigStage(Stage stage) {
        super.onConfigStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void onConfigScene(Scene scene) {
        super.onConfigScene(scene);
        scene.getStylesheets().add("css/style.css");
        WidgetCustomizer.makeDraggable(menu);
    }

    @FXML
    public void exitButtonActionHandler(ActionEvent event) {
        System.exit(0);
    }

}
