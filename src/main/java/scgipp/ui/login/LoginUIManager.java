package scgipp.ui.login;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toString());
    }
}
