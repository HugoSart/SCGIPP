package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.ui.UIManager;

public class AddUserUIManager extends UIManager {

    public AddUserUIManager() {
        super("/fxml/users_add_screen.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}
