<<<<<<< HEAD
package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javassist.NotFoundException;
import scgipp.service.UserSession;
import scgipp.service.entities.User;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Spawner;

import javax.management.InstanceAlreadyExistsException;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginScenario extends Scenario {

    @FXML private Pane rootPane;

    @FXML private HBox menu;
    @FXML private Button exitButton;
    @FXML private Button hideButton;
    @FXML private Button enterButton;
    @FXML private TextField tfUser;
    @FXML private TextField tfPassword;
    @FXML private Label errorLabel;

    public LoginScenario() {
        super("fxml/scenario_login.fxml");
    }

    @Override
    public void onConfigScene(Scene scene) {

        scene.getStylesheets().add("css/Style.css");

        enterButton.setOnAction(event -> {
            String login = tfUser.getText(), password = tfPassword.getText();
            try {
                User user = UserManager.getInstance().authenticate(login, password);
                UserSession.openSession(user);
                MainScenario mainScenario = new MainScenario();
                Spawner.startScenario(mainScenario, this);
                finish();
            } catch (InstanceAlreadyExistsException | NotFoundException e) {
                e.printStackTrace();
                errorLabel.setVisible(true);
            }
        });

    }

    @Override
    public void onConfigStage(Stage stage) {
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
        NodeCustomizer.setUpMenuBar(this, menu, exitButton, null, hideButton);
    }

}
=======
package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javassist.NotFoundException;
import scgipp.service.UserSession;
import scgipp.service.entities.User;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Spawner;

import javax.management.InstanceAlreadyExistsException;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginScenario extends Scenario {

    @FXML private Pane rootPane;

    @FXML private HBox menu;
    @FXML private Button exitButton;
    @FXML private Button hideButton;
    @FXML private Button enterButton;
    @FXML private TextField tfUser;
    @FXML private TextField tfPassword;
    @FXML private Label errorLabel;

    public LoginScenario() {
        super("fxml/scenario_login.fxml");
    }

    @Override
    public void onConfigScene(Scene scene) {

        scene.getStylesheets().add("css/Style.css");

        enterButton.setOnAction(event -> {
            String login = tfUser.getText(), password = tfPassword.getText();
            try {
                User user = UserManager.getInstance().authenticate(login, password);
                UserSession.openSession(user);
                MainScenario mainScenario = new MainScenario();
                Spawner.startScenario(mainScenario, this);
                finish();
            } catch (InstanceAlreadyExistsException | NotFoundException e) {
                e.printStackTrace();
                errorLabel.setVisible(true);
            }
        });

    }

    @Override
    public void onConfigStage(Stage stage) {
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
        NodeCustomizer.setUpMenuBar(this, menu, exitButton, null, hideButton);
    }

}
>>>>>>> [C]ObservableCustomer
