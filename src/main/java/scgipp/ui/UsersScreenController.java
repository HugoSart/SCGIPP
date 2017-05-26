package scgipp.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersScreenController implements Initializable {

    private UserManager userManager;
    private ObservableList observableList;

    public void initialize(URL location, ResourceBundle resources) {

        userManager = new UserManager();
        //observableList = FXCollections.observableList(userManager.getAll());

    }
}
