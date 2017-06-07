package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.user_management.User;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.UserPermissionsUIController;

public class UserPermissionsUIManager extends UIManager {

    private User user;

    public UserPermissionsUIManager(User user) {
        super("/fxml/users_edit_permissions.fxml");
        this.user = user;
    }

    @Override
    public void initializeData(FXMLLoader loader) {
        UserPermissionsUIController userPermissionsUIController = loader.getController();
        userPermissionsUIController.initData(user);
    }

    @Override
    protected void configStage(Stage stage) {
        
    }
}
