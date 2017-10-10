package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.User;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 08/10/2017<br/>
 * Time: 21:28<br/>
 */
public class UserFragment extends Fragment {

    private UserManager userManager = UserManager.getInstance();

    @FXML private AnchorPane userInfoPane;

    @FXML private TableView<User> tvUsers;
    @FXML private TableColumn<User, Integer> tcId;
    @FXML private TableColumn<User, String> tcLogin;

    @FXML private Button btTest;
    @FXML private Button btAddUser;
    @FXML private Button btRemove;

    @FXML ObservableList<User> userObservableList;

    public UserFragment() {
        super("fxml/users.fxml");
    }

    @Override
    protected void onCreateView() {

        List<User> userList = userManager.getAll();
        for (User user : userList) {
            System.out.println(user);
        }
        userObservableList = FXCollections.observableList(userList);
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        tvUsers.setItems(userObservableList);

        btAddUser.setOnAction(event -> {
            FeedbackScenario addUserScenario = new AddUserScenario();
            Spawner.newFeedbackWindow(addUserScenario, 0, (requestCode, resultCode, data) -> {
                User user = (User)data.get(AddUserScenario.FEEDBACK_NEW_USER);
                if (userManager.addUser(user) != -1)
                    userObservableList.add(user);
                tvUsers.refresh();
            });
        });

        btRemove.setOnAction(event -> {
            User user = tvUsers.getSelectionModel().getSelectedItem();
            userManager.delete(user);
            userObservableList.remove(user);
            tvUsers.refresh();
        });

        NodeCustomizer.makeMovable(btAddUser);
        tvUsers.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                User user = tvUsers.getSelectionModel().getSelectedItem();
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                userInfoFragment.putExtra("user", user);
                Spawner.newFragment(userInfoFragment, getParent(), userInfoPane);
            }
        });

    }




}
