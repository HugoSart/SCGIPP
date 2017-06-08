package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;
import scgipp.ui.controllers.AddCustomerUIController;

/**
 * Created by hugo_ on 04/06/2017.
 */
public class AddCustomerUIManager extends UIManager {

    private Customer customer;

    public AddCustomerUIManager() {
        super("/fxml/customer_add.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {
        AddCustomerUIController controller = loader.getController();
        if (customer != null) controller.initData(customer);
    }

    @Override
    protected void configStage(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public void editMode(Customer customer) {
        this.customer = customer;
    }

}
