package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.CustomerInfoUIController;

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
        stage.initModality(Modality.APPLICATION_MODAL);
    }
}
