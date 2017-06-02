package scgipp.ui.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import scgipp.ui.user.add.AddUserManager;
import scgipp.ui.user.edit.EditUserManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    @FXML private AnchorPane mainPane;

    @FXML private TableView<User> tvUsers;
    @FXML private TableColumn<User, Integer> tcId;
    @FXML private TableColumn<User, String> tcUser;
    @FXML private TableColumn<User, String> tcPassword;

    private UserManager userManager;
    private EditUserManager editUserManager;
    private ObservableList<User> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        userManager = new UserManager();
        editUserManager = new EditUserManager();
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
                    editUserManager.newWindow(rowData);
                }
            });
            return row ;
        });

    }

    public void btCloseActionHandler(ActionEvent event) {
        mainPane.getScene().getWindow().hide();
    }

    public void btAddActionHandler(ActionEvent event) throws IOException {
        AddUserManager manajer = new AddUserManager();
        manajer.newWindow();
    }

    public void updateTable() {

    }

}
