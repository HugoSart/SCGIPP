package scgipp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.session_management.UserSession;

public class LoginController {

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
            alert.setContentText("Verifique se o usuario e senha estão corretos.");

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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/main_screen.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("SCGIPP");
        stage.setMaximized(true);
        stage.setScene(new Scene(root));

        mainPane.getScene().getWindow().hide();

        stage.show();

    }

}
