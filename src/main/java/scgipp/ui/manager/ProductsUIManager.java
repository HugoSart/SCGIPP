package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.product_management.Product;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class ProductsUIManager extends UIManager {

    public ProductsUIManager() {
        super("/fxml/products.fxml");
    }


    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {

    }
}
