package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import scgipp.service.user_management.User;
import scgipp.ui.manager.DialogManager;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPropertiesUIController implements Initializable {

    @FXML private Label lbUser;
    @FXML private Label lbId;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btChangePasswordActionHandler(ActionEvent event) {
        DialogManager.changePassword(user);
    }

    public void initData(User user) {
        this.user = user;
        lbUser.setText(user.getLogin());
        lbId.setText(user.getId().toString());
    }

}
