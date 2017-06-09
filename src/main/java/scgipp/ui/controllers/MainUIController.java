package scgipp.ui.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.data.hibernate.DataAccess;
import scgipp.service.session_management.UserSession;
import scgipp.service.supplier_management.Supplier;
import scgipp.ui.manager.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    @FXML private AnchorPane ap;
    @FXML private Pane contentPane;
    @FXML private Button btUsers;
    @FXML private Button btCustomers;
    @FXML private HBox hbox;
    @FXML private HBox menuButtons;

    double xOffset, yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btUsersActionHandler(ActionEvent event) {
        try {
            openUsersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        menuButtonClicked((Button)event.getSource());
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
        menuButtonClicked((Button)event.getSource());
    }

    public void btCustomersActionHandler(ActionEvent event) {
        try {
            openCustomersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        menuButtonClicked((Button)event.getSource());
    }

    public void btProductsActionHandler(ActionEvent event) {
        try {
            openProductsStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hboxMousePressedHandler(MouseEvent event) {
        xOffset = ((Node)event.getSource()).getScene().getWindow().getX() - event.getScreenX();
        yOffset = ((Node)event.getSource()).getScene().getWindow().getY() - event.getScreenY();

    }

    public void hboxMouseDraggedHandler(MouseEvent event) {

        if (((Stage) ((Node) event.getSource()).getScene().getWindow()).isMaximized()) return;

        ((Node)event.getSource()).getScene().getWindow().setX(event.getScreenX() + xOffset);
        ((Node)event.getSource()).getScene().getWindow().setY(event.getScreenY() + yOffset);

    }

    public void hboxMouseClickedHandler(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ((Stage)((Node) event.getSource()).getScene().getWindow()).setMaximized(!((Stage) ((Node) event.getSource()).getScene().getWindow()).isMaximized());
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

    private void openProductsStage() throws Exception {
        ProductsUIManager productsUIManager = new ProductsUIManager();
        Stage stage = productsUIManager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Produtos");
        stage.show();
    }

    public void btTransportadoraActionHandler (ActionEvent actionEvent) {
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

    private void menuButtonClicked(Button button) {

        button.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public void btSupplierActionHandler(ActionEvent actionEvent) {
        try {
            openSupplierStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openSupplierStage() {
        SupplierUIManager supUIManager = new SupplierUIManager();
        Stage stage = supUIManager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("FORNECEDOR");
        stage.show();
    }
}
