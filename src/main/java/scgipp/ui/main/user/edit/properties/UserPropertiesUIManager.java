package scgipp.ui.main.user.edit.properties;

import javafx.fxml.FXMLLoader;
import scgipp.service.user_management.User;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 03/06/2017.
 */
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

}
