package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.CustomerPropertiesUIController;

/**
 * Created by hugo_ on 05/06/2017.
 */
public class CustomerPropertiesUIManager extends UIManager {

    private Customer customer;

    public CustomerPropertiesUIManager(Customer customer) {
        super("/fxml/customer_properties.fxml");
        this.customer = customer;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        CustomerPropertiesUIController controller =loader.getController();
        controller.initData(customer);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
