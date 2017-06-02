package scgipp.ui.user.add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import scgipp.ui.user.UsersController;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML private AnchorPane mainPane;

    @FXML private ComboBox<Permissions.UserType> cbType;
    @FXML private TextField tfLogin;
    @FXML private TextField tfPassword;

    public void btAddActionHandler(ActionEvent event) {

        UserManager userManager = new UserManager();
        User user = userManager.register(tfLogin.getText(), tfPassword.getText(), cbType.getValue());

        //TableView<User> tvUser = (TableView<User>)((Node)event.getTarget()).getScene().lookup("#tvUsers");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/users_screen.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsersController usersController = loader.getController();

        ((Node)event.getTarget()).getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Permissions.UserType> list = FXCollections.observableList(Arrays.asList(Permissions.UserType.values()));
        cbType.setItems(list);
    }
}
