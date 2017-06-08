package scgipp.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.session_management.UserSession;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.AddUserUIManager;
import scgipp.ui.manager.UserInfoUIManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersUIController implements Initializable {

    @FXML private AnchorPane mainPane;

    @FXML private Button btAdd;
    @FXML private Button btRemove;

    @FXML private TableView<User> tvUsers;
    @FXML private TableColumn<User, Integer> tcId;
    @FXML private TableColumn<User, String> tcUser;
    @FXML private TableColumn<User, String> tcPassword;

    private UserManager userManager;
    private UserInfoUIManager userInfoUIManager;
    private ObservableList<User> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        initViews();

        userManager = new UserManager();
        observableList = FXCollections.observableList(userManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("login"));
        tcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        tvUsers.setItems(observableList);

        tvUsers.setRowFactory( tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    User rowData = row.getItem();
                    userInfoUIManager = new UserInfoUIManager(rowData);
                    Stage stage = userInfoUIManager.newStage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            });
            return row ;
        });

    }

    public void btCloseActionHandler(ActionEvent event) {
        mainPane.getScene().getWindow().hide();
    }

    public void btAddActionHandler(ActionEvent event) throws IOException {
        AddUserUIManager manajer = new AddUserUIManager();
        Stage stage = manajer.newStage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().focusedProperty().addListener((observable, oldValue, newValue) -> updateTable());
    }

    public void btRemoveActionHandler(ActionEvent event) {
        (new UserManager()).remove(tvUsers.getSelectionModel().getSelectedItem());
        updateTable();
    }

    public void initViews() {
        btAdd.setManaged(UserSession.getUser().getPermissions().check(Permissions.Permission.USER_ADD));
        btRemove.setManaged(UserSession.getUser().getPermissions().check(Permissions.Permission.USER_REMOVE));
    }

    private void updateTable() {
        observableList = FXCollections.observableList(userManager.getAll());
        tvUsers.setItems(observableList);
        tvUsers.refresh();
    }

}
