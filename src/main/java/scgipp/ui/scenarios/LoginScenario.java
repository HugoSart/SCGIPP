package scgipp.ui.scenarios;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javassist.NotFoundException;
import scgipp.service.UserSession;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Spawner;

import javax.management.InstanceAlreadyExistsException;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 27/08/2017<br/>
 * Time: 18:01<br/>
 */
public class LoginScenario extends Scenario {

    @FXML private HBox menu;
    @FXML private Button exitButton;
    @FXML private Button hideButton;
    @FXML private Button enterButton;
    @FXML private TextField tfUser;
    @FXML private TextField tfPassword;
    @FXML private Label errorLabel;

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
        NodeCustomizer.setUpMenuBar(this, menu, exitButton, null, hideButton);

        enterButton.setOnAction(event -> {
            String login = tfUser.getText(), password = tfPassword.getText();
            try {
                UserSession.openSession(UserManager.authenticate(login, password));
                MainScenario mainScenario = new MainScenario();
                Spawner.newWindow(mainScenario, this);
                finish();
            } catch (InstanceAlreadyExistsException | NotFoundException e) {
                errorLabel.setVisible(true);
            }
        });

    }


}
