package scgipp.ui.scenarios;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 23:07<br/>
 */
public class UserInfoFragment extends Fragment {

    @FXML private TextField tfLogin;
    @FXML private ListView<CheckBox> lvPermissions;

    @FXML private Button btEdit;
    @FXML private Button btChangePassword;
    @FXML private MaterialDesignIconView icon;

    private User user;

    private boolean editMode = false;

    public UserInfoFragment() {
        super("fxml/fragment_user_info.fxml");
    }

    @Override
    protected void onCreateView() {

        user = (User)getExtra("user");
        tfLogin.setText(user.getLogin());

        List<CheckBox> checkBoxList = new ArrayList<>();
        for (Permissions.Permission permission : Permissions.Permission.values()) {
            CheckBox cb = new CheckBox();
            cb.setText(permission.name());
            cb.setSelected(user.getPermissions().check(permission));
            checkBoxList.add(cb);
        }

        lvPermissions.setItems(FXCollections.observableArrayList(checkBoxList));

        btEdit.setOnAction(event -> {

            tfLogin.setEditable(false);
            btChangePassword.setDisable(editMode);
            lvPermissions.setDisable(editMode);
            icon.setGlyphName(!editMode ? "CHECK" : "PENCIL");

            if (editMode) {
                user.permissions.removeAll();
                for (CheckBox cb : checkBoxList)
                    if (cb.isSelected())
                        user.permissions.add(Permissions.Permission.valueOf(cb.getText()));
                UserManager.getInstance().updateUser(user);
            }

            editMode = !editMode;

        });

        btChangePassword.setOnAction(event -> {
            ChangePasswordScenario changePasswordScenario = new ChangePasswordScenario();
            changePasswordScenario.putExtra("user", user);
            Spawner.startFeedbackScenario(changePasswordScenario, 0, this, (requestCode, resultCode, data) -> {
                if (resultCode == 1) {
                    UserManager.getInstance().updateUser(((User)data.get("user")));
                    lvPermissions.refresh();
                }
            });
        });

    }

}
