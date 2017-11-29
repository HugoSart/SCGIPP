package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 17:03<br/>
 */
public class AddUserScenario extends FeedbackScenario {

    private UserManager userManager = UserManager.getInstance();

    public static final String FEEDBACK_NEW_USER = "new_user";

    @FXML private HBox menuBar;
    @FXML private Label lbSessionUser1;
    @FXML private Button btExit;
    @FXML private Button btOk;
    @FXML private Button btCancel;
    @FXML private TextField tfLogin;
    @FXML private PasswordField tfPassword;
    @FXML private PasswordField tfConfirmPassword;
    @FXML private Label lbLoginAlreadyExists;
    @FXML private Label lbPasswordDoesNotMatch;
    @FXML private ListView<CheckBox> lvPermissions;

    public AddUserScenario() {
        super("fxml/scenario_add_user.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {

        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, null, null);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        btOk.setOnAction(event -> {

            String login = tfLogin.getText(), password = tfPassword.getText(), passwordConfirmation = tfConfirmPassword.getText();

            boolean loginAlreadyRegistered = false;
            for (User user : userManager.getAll()) {
                if (user.getLogin().equals(login) || tfLogin.getText().isEmpty()) {
                    loginAlreadyRegistered = true;
                    break;
                }
            }

            lbLoginAlreadyExists.setVisible(loginAlreadyRegistered);
            lbPasswordDoesNotMatch.setVisible(!password.equals(passwordConfirmation));

            if (!loginAlreadyRegistered && password.equals(passwordConfirmation)) {

                User user = new User(login, password);

                for (CheckBox checkBox : lvPermissions.getItems()) {
                    if (checkBox.isSelected()) {
                        user.permissions.add(Permissions.Permission.valueOf(checkBox.getText()));
                    }
                }

                putFeedback(FEEDBACK_NEW_USER, user);
                processFeedbackAndFinish();
            }


        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);

        List<CheckBox> permissionsCheckBoxList = new ArrayList<>();
        for (Permissions.Permission permission : Permissions.Permission.values()) {
            CheckBox cb = new CheckBox();
            cb.setText(permission.toString());
            permissionsCheckBoxList.add(cb);
        }
        lvPermissions.setItems(FXCollections.observableArrayList(permissionsCheckBoxList));

    }
}
