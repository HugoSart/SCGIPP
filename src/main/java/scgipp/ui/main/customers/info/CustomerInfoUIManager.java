package scgipp.ui.main.customers.info;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;

/**
 * Created by hugo_ on 05/06/2017.
 */
public class CustomerInfoUIManager extends UIManager {

    private Customer customer;

    public CustomerInfoUIManager(Customer customer) {
        super("/fxml/customer_info.fxml");
        this.customer = customer;
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        CustomerInfoUIController controller = loader.getController();
        controller.initData(customer);
    }

    @Override
    protected void configStage(Stage stage) {

    }
}
