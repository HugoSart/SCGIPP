package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.TransportadoraInfoUIController;

/**
 * Created by kira on 07/06/17.
 */
public class TransportadoraInfoUIManager extends UIManager {
    private Transportadora transp;

    public TransportadoraInfoUIManager(Transportadora transpAlvo) {
        super("/fxml/transp_info.fxml");
        this.transp = transpAlvo;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        TransportadoraInfoUIController transpInfoUIController = loader.getController();
        transpInfoUIController.initData(transp);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
