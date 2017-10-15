package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.data.encryption.Encryptor;
import scgipp.service.entities.User;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;

/**
 * User: hugo_<br/>
 * Date: 13/10/2017<br/>
 * Time: 20:09<br/>
 */
public class ChangePasswordScenario extends FeedbackScenario {

    @FXML private HBox menuBar;
    @FXML private Button btExit;
    @FXML private Label lbSessionUser1;

    @FXML private PasswordField tfCurrentPassword;
    @FXML private PasswordField tfPassword;
    @FXML private PasswordField tfConfirmPassword;
    @FXML private Label lbPasswordDoesNotMatch;
    @FXML private Label lbIncorrectPassword;

    @FXML private Button btOk;
    @FXML private Button btCancel;

    private User user;

    public ChangePasswordScenario() {
        super("fxml/scenario_change_password.fxml");
    }

    @Override
    protected void onConfigStage(Stage stage) {
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, null, null);
        stage.getScene().getStylesheets().add("css/style.css");

        user = (User)getExtra("user");

        btOk.setOnAction(event -> {

            boolean correctPassword = Encryptor.encrypt(tfCurrentPassword.getText()).equals(user.getPassword());
            boolean correctConfirmation = tfPassword.getText().equals(tfConfirmPassword.getText());

            if (correctPassword && correctConfirmation) {
                putFeedback("user", user);
                user.setPassword(tfPassword.getText());
                setResultCode(1);
                processFeedbackAndFinish();
            } else {
                lbPasswordDoesNotMatch.setVisible(!correctConfirmation);
                lbIncorrectPassword.setVisible(!correctPassword);
            }

        });

        btCancel.setOnAction(event -> {
            finish();
        });

    }
}
