package scgipp.ui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.session_management.UserSession;
import scgipp.ui.main.MainUIManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUIController {

    @FXML Button btLogin;
    @FXML TextField tfUser;
    @FXML TextField tfPassword;

    @FXML AnchorPane mainPane;

    public void btLoginActionHandler(ActionEvent event) {

        String login = tfUser.getText(), password = tfPassword.getText();

        UserSession userSession = null;

        try {
            userSession = UserSession.open(login, password);
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha na autenticação");
            alert.setContentText(e.getMessage());

            alert.showAndWait();

            e.printStackTrace();

        }

        if (userSession != null) {
            try {
                openMainStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void openMainStage() throws Exception {

        MainUIManager mainUIManager = new MainUIManager();

        Stage stage = mainUIManager.newStage();
        stage.setTitle("SCGIPP");
        stage.setMaximized(true);
        mainPane.getScene().getWindow().hide();

        stage.show();

    }


}
