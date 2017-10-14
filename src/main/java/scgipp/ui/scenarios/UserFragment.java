package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.User;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Spawner;

import java.util.List;

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

    @FXML private TextField tfSearch;
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

        userInfoPane.setVisible(false);

        userObservableList = FXCollections.observableList(userList);
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        tvUsers.setItems(userObservableList);

        btAddUser.setOnAction(event -> {
            FeedbackScenario addUserScenario = new AddUserScenario();
            Spawner.startFeedbackScenario(addUserScenario, 0, this, (requestCode, resultCode, data) -> {
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
            userInfoPane.setVisible(false);
        });

        tvUsers.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                userInfoPane.setVisible(true);
                User user = tvUsers.getSelectionModel().getSelectedItem();
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                userInfoFragment.putExtra("user", user);
                Spawner.startFragment(userInfoFragment, getParentController(), userInfoPane);
            }
        });

        FilteredList<User> filteredData = new FilteredList<>(userObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getLogin()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvUsers.setItems(filteredData);

    }




}
