package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.session_management.UserSession;
import scgipp.ui.manager.LoginUIManager;
import scgipp.ui.manager.CustomersUIManager;
import scgipp.ui.manager.TransportadoraUIManager;
import scgipp.ui.manager.UsersUIManager;

public class MainUIController {

    @FXML private AnchorPane ap;
    @FXML private AnchorPane apContent;

    @FXML private Button btUsers;

    public void btUsersActionHandler(ActionEvent arg0) {
        try {
            openUsersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btSairActionHandler(ActionEvent event) {
        UserSession.close();
        DataAccess.close();
        System.exit(0);
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
        usersUIManager.newStage().show();

    }

    private void openCustomersStage() throws Exception {
        CustomersUIManager customersUIManager = new CustomersUIManager();
        Stage stage = customersUIManager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Clientes");
        stage.show();
    }

    public void btTransportadoraActionHandler(ActionEvent actionEvent) {
        try {
            openTransportadoraStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openTransportadoraStage() {
        TransportadoraUIManager transpUIManager = new TransportadoraUIManager();
        Stage stage = transpUIManager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Transportadora");
        stage.show();
    }
}
