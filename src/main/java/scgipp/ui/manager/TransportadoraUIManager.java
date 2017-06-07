package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.ui.UIManager;

/**
 * Created by kira on 07/06/17.
 */
public class TransportadoraUIManager extends UIManager {

    public TransportadoraUIManager(){
        super("/fxml/transportadora.fxml");
    }
    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}