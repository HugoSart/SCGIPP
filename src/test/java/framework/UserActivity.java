package framework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scgipp.ui.framework.Activity;
import scgipp.ui.framework.FeedbackActivity;
import scgipp.ui.framework.Spawner;

import java.nio.channels.AcceptPendingException;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:12<br/>
 */
public class UserActivity extends Activity {

    @FXML ListView<String> lvUser;
    @FXML Button btAdd;

    private ObservableList<String> users = FXCollections.observableArrayList();

    public UserActivity() {
        super("user_list.fxml");
    }

    @Override
    public void onCreate(Stage stage) {
        users.addAll("João", "Lúcio");
    }

    @Override
    public void onReady() {
        lvUser.setItems(users);
    }

    @FXML
    public void btAddActionHandler(ActionEvent actionEvent) {
        AddUserActivity addUserActivity = new AddUserActivity();
        Spawner.newWindow(addUserActivity, "addUser", this);
    }

    @FXML
    public void btEditActionHandler(ActionEvent actionEvent) {
        EditUserActivity editUserActivity = new EditUserActivity();
        editUserActivity.putExtra("name", lvUser.getSelectionModel().getSelectedItem());
        Spawner.newWindow(editUserActivity, "editUser", this);
    }

    @Override
    public void onFeedback(String id) {
        super.onFeedback(id);

        switch (id) {
            case "addUser":

                String name1 = (String)getChildren(id).getExtra("return_user");
                users.add(name1);

                lvUser.refresh();

                break;
            case "editUser":

                String name2 = (String)getChildren(id).getExtra("return_newName");
                users.add(name2);
                lvUser.refresh();

                break;
        }

    }

}
