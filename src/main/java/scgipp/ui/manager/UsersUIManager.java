package scgipp.ui.manager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.UsersUIController;

public class UsersUIManager extends UIManager {

    public UsersUIManager() {
        super("/fxml/users.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {
        stage.setTitle("SCGIPP");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

}
