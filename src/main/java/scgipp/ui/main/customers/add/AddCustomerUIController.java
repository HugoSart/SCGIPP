package scgipp.ui.main.customers.add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import scgipp.service.Adress;
import scgipp.service.customer_management.Customer;
import scgipp.service.customer_management.CustomerManager;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 04/06/2017.
 */
public class AddCustomerUIController implements Initializable {

    @FXML private ChoiceBox<Customer.Type> cbType;

    @FXML private TextField tfName;
    @FXML private TextField tfCPF;
    @FXML private TextField tfPhone;
    @FXML private TextField tfCountry;
    @FXML private TextField tfState;
    @FXML private TextField tfCity;
    @FXML private TextField tfStreet;
    @FXML private TextField tfNumber;
    @FXML private TextField tfComp;
    @FXML private TextField tfZip;

    @FXML private DatePicker dpData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Customer.Type> observableList = FXCollections.observableList(Arrays.asList(Customer.Type.values()));
        cbType.setItems(observableList);
    }

    public void btConfirmActionHandler(ActionEvent event) {

        String name = tfName.getText(), cpf = tfCPF.getText(), phone = tfPhone.getText(),
                country = tfCountry.getText(), state = tfState.getText(), city = tfCity.getText(), street = tfStreet.getText(), number = tfNumber.getText(), comp = tfComp.getText(), zip = tfZip.getText();

        Customer.Type type = cbType.getSelectionModel().getSelectedItem();

        LocalDate date = dpData.getValue();
        Adress adress = new Adress(country, state, city, street, number, comp, zip);

        Customer customer = new Customer(type, name, cpf, date);
        customer.addAdress(adress);

        CustomerManager customerManager = new CustomerManager();
        customerManager.register(customer);

        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void btCancelActionHandler(ActionEvent event) {

        ((Node)event.getSource()).getScene().getWindow().hide();

    }

}
