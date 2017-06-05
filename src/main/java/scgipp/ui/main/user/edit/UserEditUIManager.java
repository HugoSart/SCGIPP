package scgipp.ui.main.user.edit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.user_management.User;
import scgipp.ui.UIManager;

import java.io.IOException;

public class UserEditUIManager extends UIManager {

    private User user;

    public UserEditUIManager(User user) {
        super("/fxml/users_edit.fxml");
        this.user = user;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        UserEditUIController userEditUIController = loader.getController();
        userEditUIController.initData(user);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
