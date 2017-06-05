package scgipp.ui.main.user;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 03/06/2017.
 */
public class UsersUIManager extends UIManager {

    public UsersUIManager() {
        super("/fxml/users.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}
