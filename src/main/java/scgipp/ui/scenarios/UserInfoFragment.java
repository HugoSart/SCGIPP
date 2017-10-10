package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.ui.FXScenario.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 23:07<br/>
 */
public class UserInfoFragment extends Fragment {

    @FXML private TextField tfLogin;
    @FXML private ListView<CheckBox> lvPermissions;

    private User user;

    public UserInfoFragment() {
        super("fxml/fragment_user_info.fxml");
    }

    @Override
    protected void onCreateView() {
        user =(User)getExtra("user");
        tfLogin.setText(user.getLogin());

        List<CheckBox> checkBoxList = new ArrayList<>();
        for (Permissions.Permission permission : Permissions.Permission.values()) {
            CheckBox cb = new CheckBox();
            cb.setText(permission.name());
            cb.setSelected(user.getPermissions().check(permission));
            checkBoxList.add(cb);
        }

        lvPermissions.setItems(FXCollections.observableArrayList(checkBoxList));
    }
}
