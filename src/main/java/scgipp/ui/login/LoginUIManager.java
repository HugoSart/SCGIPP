package scgipp.ui.login;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 03/06/2017.
 */
public class LoginUIManager extends UIManager {

    public LoginUIManager() {
        super("/fxml/login.fxml");
    }

    @Override
    public void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}
