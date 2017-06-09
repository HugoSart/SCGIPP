package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.product_management.Product;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.ProductAddUIController;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class ProductAddUIManager extends UIManager {

    private Product product;

    public ProductAddUIManager() {
        super("/fxml/product_add.fxml");
    }

    public ProductAddUIManager(Product product) {
        super("/fxml/product_add.fxml");
        this.product = product;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        ProductAddUIController productAddUIController = loader.getController();
        productAddUIController.initData(product);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
