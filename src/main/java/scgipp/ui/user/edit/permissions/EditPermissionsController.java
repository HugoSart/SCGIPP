package scgipp.ui.user.edit.permissions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxListCell;
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

/**
 * Created by hugo_ on 01/06/2017.
 */
public class EditPermissionsController implements Initializable {

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
