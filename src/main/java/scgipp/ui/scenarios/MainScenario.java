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
    @FXML private AnchorPane pCustomers;
    @FXML private AnchorPane pSales;
    @FXML private AnchorPane pSupplier;

    public MainScenario() {
        super("fxml/scenario_main.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
        lbSessionUser.setText(UserSession.getSession().getActiveUser().getLogin());

        Spawner.startFragment(new UserFragment(), this, pUsers);
        Spawner.startFragment(new SalesFragment(), this, pSales);
        Spawner.startFragment(new SupplierFragment(), this, pSupplier);
    }

    @Override
    protected void onConfigStage(Stage stage) {
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, btMaximize, btHide);
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }

}
