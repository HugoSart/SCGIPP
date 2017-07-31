package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import scgipp.service.Address;
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

    @FXML private Label lbPhone;

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

    @FXML private AnchorPane apAddress;

    private Customer customer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Customer.Type> observableList = FXCollections.observableList(Arrays.asList(Customer.Type.values()));
        cbType.setItems(observableList);
    }

    public void btConfirmActionHandler(ActionEvent event) {

        if (customer != null) {
            CustomerManager manager = new CustomerManager();
            customer.setName(tfName.getText());
            customer.setDate(dpData.getValue());
            customer.setCpf(tfCPF.getText());
            if (customer.getAddresses().get(0) != null) {
                customer.getAddresses().get(0).setCountry(tfCountry.getText());
                customer.getAddresses().get(0).setState(tfState.getText());
                customer.getAddresses().get(0).setCity(tfCity.getText());
                customer.getAddresses().get(0).setStreet(tfStreet.getText());
                customer.getAddresses().get(0).setNumber(tfNumber.getText());
                customer.getAddresses().get(0).setComp(tfComp.getText());
                customer.getAddresses().get(0).setZip(tfZip.getText());
            }
            manager.update(customer);
            return;
        }

        String name = tfName.getText(), cpf = tfCPF.getText(), phone = tfPhone.getText(),
                country = tfCountry.getText(), state = tfState.getText(), city = tfCity.getText(), street = tfStreet.getText(), number = tfNumber.getText(), comp = tfComp.getText(), zip = tfZip.getText();

        Customer.Type type = cbType.getSelectionModel().getSelectedItem();

        LocalDate date = dpData.getValue();
        Address address = new Address(country, state, city, street, number, comp, zip);

        Customer customer = new Customer(type, name, cpf, date);
        customer.addAdress(address);
        customer.addPhone(phone);
        CustomerManager customerManager = new CustomerManager();
        customerManager.register(customer);

        System.out.printf("aaa");
        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void btCancelActionHandler(ActionEvent event) {

        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void initData(Customer customer) {
        this.customer = customer;
        tfName.setText(customer.getName());
        tfCPF.setText(customer.getCpf());
        tfPhone.setText(customer.getPhones().get(0));
        tfCountry.setText(customer.getAddresses().get(0).getCountry());
        tfState.setText(customer.getAddresses().get(0).getState());
        tfCity.setText(customer.getAddresses().get(0).getCity());
        tfStreet.setText(customer.getAddresses().get(0).getStreet());
        tfNumber.setText(customer.getAddresses().get(0).getNumber());
        tfComp.setText(customer.getAddresses().get(0).getComp());
        tfZip.setText(customer.getAddresses().get(0).getZip());
    }

}
