package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddUserUIController implements Initializable {

    @FXML private AnchorPane mainPane;

    @FXML private ComboBox<Permissions.UserType> cbType;
    @FXML private TextField tfLogin;
    @FXML private TextField tfPassword;

    public void btAddActionHandler(ActionEvent event) {

        UserManager userManager = new UserManager();
        User user = userManager.register(tfLogin.getText(), tfPassword.getText(), cbType.getValue());
        ((Node)event.getTarget()).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Permissions.UserType> list = FXCollections.observableList(Arrays.asList(Permissions.UserType.values()));
        cbType.setItems(list);
    }

}
