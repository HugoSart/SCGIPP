package scgipp.ui.scenarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.NodeCustomizer;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginScenario extends Scenario {

    @FXML private HBox menu;
    @FXML private Button exitButton;
    @FXML private Button enterButton;

    public LoginScenario() {
        super("fxml/login.fxml");
    }

    @Override
    public void onConfigStage(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/style.css");
        NodeCustomizer.makeDraggable(menu);
    }

    @FXML
    public void exitButtonActionHandler(ActionEvent event) {
        System.exit(0);
    }

}
