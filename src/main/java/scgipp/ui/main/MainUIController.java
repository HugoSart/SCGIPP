package scgipp.ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.session_management.UserSession;
import scgipp.ui.login.LoginUIManager;
import scgipp.ui.main.customers.CustomersUIManager;
import scgipp.ui.main.user.UsersUIManager;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class MainUIController {

    @FXML private AnchorPane ap;

    @FXML private Button btUsers;

    public void btUsersActionHandler(ActionEvent arg0) {
        try {
            openUsersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btChangeUserActionHandler(ActionEvent event) {
        try {
            openLoginStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btCustomersActionHandler(ActionEvent event) {
        try {
            openCustomersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openLoginStage() throws Exception {
        UserSession.close();
        LoginUIManager loginUIManager = new LoginUIManager();
        Stage stage = loginUIManager.newStage();
        ap.getScene().getWindow().hide();
        stage.show();
    }

    private void openUsersStage() throws Exception {

        UsersUIManager usersUIManager = new UsersUIManager();
        Stage stage = usersUIManager.newStage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Usuários");
        stage.show();

    }

    private void openCustomersStage() throws Exception {
        CustomersUIManager customersUIManager = new CustomersUIManager();
        Stage stage = customersUIManager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Clientes");
        stage.show();
    }

}
