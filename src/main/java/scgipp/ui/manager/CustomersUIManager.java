package scgipp.ui.manager;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.ui.UIManager;

public class CustomersUIManager extends UIManager {


    public CustomersUIManager() {
        super("/fxml/customers.fxml");
    }

    @Override
    protected void initializeData(FXMLLoader loader) {

    }

    @Override
    protected void configStage(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }
}
