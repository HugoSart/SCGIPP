package scgipp.ui.main.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import scgipp.service.session_management.UserSession;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import scgipp.ui.main.user.add.AddUserUIManager;
import scgipp.ui.main.user.edit.UserEditUIManager;

import java.io.IOException;
import java.net.URL;
import java.security.Security;
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
    private UserEditUIManager userEditUIManager;
    private ObservableList<User> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        initViews();

        userManager = new UserManager();
        userEditUIManager = new UserEditUIManager();
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
                    userEditUIManager.newWindow(rowData);
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
        manajer.newWindow();
    }

    public void btRemoveActionHandler(ActionEvent event) {
        (new UserManager()).remove(tvUsers.getSelectionModel().getSelectedItem());
    }

    public void initViews() {
        btAdd.setManaged(UserSession.getUser().getPermissions().check(Permissions.Permission.USER_ADD));
        btRemove.setManaged(UserSession.getUser().getPermissions().check(Permissions.Permission.USER_REMOVE));
    }

}
