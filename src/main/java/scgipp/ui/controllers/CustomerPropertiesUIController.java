package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import scgipp.service.Adress;
import scgipp.service.Person;
import scgipp.service.customer_management.Customer;
import scgipp.ui.manager.AddCustomerUIManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPropertiesUIController implements Initializable {

    @FXML private Label lbType,
                        lbName,
                        lbPhone,
                        lbCPF,
                        lbSCPF,
                        lbDate,
                        lbSDate,
                        lbCountry,
                        lbState,
                        lbCity,
                        lbStreet,
                        lbNumber,
                        lbComp,
                        lbZip;

    private Customer customer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initData(Customer customer) {
        this.customer = customer;
        initViews();
    }

    public void btEditActionHandler(ActionEvent event) {
        AddCustomerUIManager addCustomerUIManager = new AddCustomerUIManager();
        addCustomerUIManager.editMode(customer);
        Stage stage = addCustomerUIManager.newStage();
        stage.show();
    }

    private void initViews() {
        if (customer.getType() == Person.Type.LEGAL) {
            lbSCPF.setText("CNPJ");
            lbSDate.setText("Funcação");
        }

        lbType.setText(customer.getType().name());
        lbName.setText(customer.getName());
        lbCPF.setText(customer.getCpf());
        lbDate.setText(customer.getDate().toString());

         lbPhone.setText(customer.getPhones().get(0));
        Adress address = customer.getAdresses().get(0);

        lbCountry.setText(address.getCountry());
        lbState.setText(address.getState());
        lbCity.setText(address.getCity());
        lbStreet.setText(address.getStreet());
        lbNumber.setText(address.getNumber());
        lbComp.setText(address.getComp());
        lbZip.setText(address.getZip());

    }

}
