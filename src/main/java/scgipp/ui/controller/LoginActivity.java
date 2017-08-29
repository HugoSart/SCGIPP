package scgipp.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.framework.Activity;
import scgipp.ui.framework.WidgetCustomizer;

import java.awt.event.ActionEvent;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginActivity extends Activity {

    @FXML private HBox menu;
    @FXML private Button exitButton;

    public LoginActivity(Activity parent) {
        super(parent, "fxml/login.fxml");
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
        WidgetCustomizer.makeKiller(exitButton);
    }




}
