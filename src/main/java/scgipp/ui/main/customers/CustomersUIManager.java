package scgipp.ui.main.customers;

import javafx.fxml.FXMLLoader;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;

public class CustomersUIManager extends UIManager {


    public CustomersUIManager() {
        super("/fxml/customers.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

}
