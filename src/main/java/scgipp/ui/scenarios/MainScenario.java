package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.UserSession;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 22:00<br/>
 */
public class MainScenario extends Scenario {

    @FXML private HBox menuBar;
    @FXML private Button btExit;
    @FXML private Button btMaximize;
    @FXML private Button btHide;
    @FXML private Label lbSessionUser;

    public MainScenario() {
        super("fxml/main.fxml");
    }

    @Override
    protected void onConfigStage(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/style.css");
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, btMaximize, btHide);
        UserSession.getSession().getActiveUser();
    }



}
