package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.AddTransportadoraUIController;

/**
 * Created by kira on 07/06/17.
 */

public class AddTransportadoraUIManager extends UIManager {

    private Transportadora transp;

    public AddTransportadoraUIManager() {
        super("/fxml/transp_add_info.fxml");
    }

    public AddTransportadoraUIManager(Transportadora transpAlvo) {
        super("/fxml/transp_add_info.fxml");
        this.transp = transpAlvo;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        AddTransportadoraUIController transpAddUIController = loader.getController();
        transpAddUIController.initData(transp);
    }

    @Override
    protected void configStage(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }
}
