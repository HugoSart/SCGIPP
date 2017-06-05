package scgipp.ui.main.user.add;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.ui.UIManager;
import scgipp.ui.main.customers.add.AddCustomerUIManager;

import java.io.IOException;

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
