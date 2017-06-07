package scgipp.ui.main.user.info.properties;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.user_management.User;
import scgipp.ui.UIManager;

public class UserPropertiesUIManager extends UIManager {

    private User user;

    public UserPropertiesUIManager(User user) {
        super("/fxml/user_properties.fxml");
        this.user = user;
    }

    @Override
    public void initializeData(FXMLLoader loader) {
        UserPropertiesUIController userPropertiesUIController = loader.getController();
        userPropertiesUIController.initData(user);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
