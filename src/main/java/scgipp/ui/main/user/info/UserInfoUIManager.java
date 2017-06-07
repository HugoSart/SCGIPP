package scgipp.ui.main.user.info;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.user_management.User;
import scgipp.ui.UIManager;

public class UserInfoUIManager extends UIManager {

    private User user;

    public UserInfoUIManager(User user) {
        super("/fxml/users_info.fxml");
        this.user = user;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        UserInfoUIController userInfoUIController = loader.getController();
        userInfoUIController.initData(user);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
