package scgipp.ui.main.user.info.permissions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;

import java.net.URL;
import java.util.*;

public class UserPermissionsUIController implements Initializable {

    @FXML private ListView<CheckBox> listPermissions;

    private UserManager userManager = new UserManager();

    private User user;

    private ObservableList<CheckBox> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(User user) {
        this.user = user;
        System.out.println("" + user.getPermissions());
        initList();
    }

    public void initList() {

        List<CheckBox> list = new ArrayList<>();

        for (Permissions.Permission p : Arrays.asList(Permissions.Permission.values())) {
            CheckBox cb = new CheckBox();
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    user.getPermissions().add(Permissions.Permission.valueOf(cb.getText()));
                } else {
                    user.getPermissions().remove(Permissions.Permission.valueOf(cb.getText()));
                }

                if (oldValue != newValue) {
                    userManager.update(user);
                }
            });
            cb.mnemonicParsingProperty().set(false);
            cb.setText(p.toString());
            cb.setSelected(user.getPermissions().check(p));
            list.add(cb);
        }

        observableList = FXCollections.observableList(list);
        listPermissions.setItems(observableList);
    }

}
