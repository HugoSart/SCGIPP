package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.UIManager;

public class MainUIManager extends UIManager {

    public MainUIManager() {
        super("/fxml/main.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }



    @Override
    protected void configStage(Stage stage) {
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toString());
    }
}
