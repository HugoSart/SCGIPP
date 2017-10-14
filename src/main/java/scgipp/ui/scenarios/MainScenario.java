package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import scgipp.service.UserSession;
import scgipp.ui.FXScenario.*;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 22:00<br/>
 */
public class MainScenario extends Scenario {

    @FXML private Pane rootPane;

    @FXML private HBox menuBar;
    @FXML private Button btExit;
    @FXML private Button btMaximize;
    @FXML private Button btHide;
    @FXML private Label lbSessionUser;

    @FXML private AnchorPane pUsers;

    public MainScenario() {
        super("fxml/main.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/style.css");
        lbSessionUser.setText(UserSession.getSession().getActiveUser().getLogin());

        Fragment fragmentScenario = new UserFragment();
        Spawner.startFragment(fragmentScenario, this, pUsers);

    }

    @Override
    protected void onConfigStage(Stage stage) {
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, btMaximize, btHide);
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }

}
