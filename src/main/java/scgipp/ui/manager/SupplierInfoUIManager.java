package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.supplier_management.Supplier;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.SupplierInfoUIController;

/**
 * Created by kira on 08/06/17.
 */
public class SupplierInfoUIManager extends UIManager {
    private Supplier sup;

    public SupplierInfoUIManager(Supplier supAlvo) {
        super("/fxml/supplierr_info.fxml");
        this.sup = supAlvo;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        SupplierInfoUIController supInfoUIController = loader.getController();
        supInfoUIController.initData(sup);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
