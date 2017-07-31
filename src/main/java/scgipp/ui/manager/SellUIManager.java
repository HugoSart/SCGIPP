package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.ui.UIManager;
import scgipp.ui.util.ResizeHelper;

public class SellUIManager extends UIManager {


    public SellUIManager() {
        super("/fxml/sell.fxml");
    }


    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {
        ResizeHelper.addResizeListener(stage);
        stage.setTitle("VENDA");
        stage.getScene().getStylesheets().add(getClass().getResource("/css/DarkTheme.css").toString());
    }


}
