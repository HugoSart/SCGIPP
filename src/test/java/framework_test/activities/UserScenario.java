package framework_test.activities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;

import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:12<br/>
 */
public class UserScenario extends Scenario implements FeedbackScenario.FeedbackListener {

    public final int REQUEST_ADD_USER = 0;

    @FXML ListView<String> lvUser;
    @FXML Button btAdd;

    private ObservableList<String> users = FXCollections.observableArrayList();

    public UserScenario() {
        super("user_list.fxml");
    }

    @Override
    public void onCreate() {
        users.addAll("João", "Lúcio");
    }

    @Override
    public void onReady() {
        lvUser.setItems(users);
    }

    @Override
    public void onFeedback(int requestCode, int resultCode, Map data) {

        if (requestCode == 3) {
            users.add((String)data.get("userName"));
            lvUser.refresh();
        }

    }

    @FXML
    public void btAddActionHandler(ActionEvent actionEvent) {
        FeedbackScenario addUserActivity = new AddUserScenario();
        Spawner.newWindow(addUserActivity, 3,this);
    }

    @FXML
    public void btEditActionHandler(ActionEvent actionEvent) {
        //Spawner.newWindow(editUserActivity, "editUser", this);
    }

}
