package scgipp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import scgipp.service.session_management.UserSession;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;

import javax.print.DocFlavor;
import javax.security.auth.login.LoginContext;
import javax.security.sasl.AuthenticationException;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hsart on 13/05/17.
 */
public class LoginScreenController {

    @FXML Button btLogin;
    @FXML TextField tfUser;
    @FXML TextField tfPassword;

    public void btLoginActionHandler(ActionEvent arg0) {

        UserManager userManager = new UserManager();

        String login = tfUser.getText(), password = tfPassword.getText();

        try {
            UserSession.open(login, password);
        } catch (AuthenticationException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha na autenticação");
            alert.setContentText("Verifique se o usuario e senha estão corretos.");

            alert.showAndWait();

            e.printStackTrace();
        }

    }

}
