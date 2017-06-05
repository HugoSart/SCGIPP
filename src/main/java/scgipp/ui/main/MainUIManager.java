package scgipp.ui.main;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 03/06/2017.
 */
public class MainUIManager extends UIManager {

    public MainUIManager() {
        super("/fxml/main.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}
