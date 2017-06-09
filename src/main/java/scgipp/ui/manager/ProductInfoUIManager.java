package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.product_management.Product;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class ProductInfoUIManager extends UIManager {

    private Product product;

    public ProductInfoUIManager(Product product) {
        super("/fxml/product_info");
        this.product = product;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }

}
