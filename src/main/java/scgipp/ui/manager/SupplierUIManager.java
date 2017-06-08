package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.UIManager;

/**
 * Created by kira on 08/06/17.
 */
public class SupplierUIManager extends UIManager {

    public SupplierUIManager() {
        super("/fxml/supplier.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.getScene().getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toString());
    }
}
