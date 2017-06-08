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
import scgipp.service.Adress;
import scgipp.service.Person;
import scgipp.service.customer_management.Customer;
import scgipp.service.supplier_management.Supplier;
import scgipp.service.supplier_management.SupplierManager;
import static scgipp.service.Person.*;


import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by kira on 08/06/17.
 */
public class AddSupplierUIController implements Initializable{
    @FXML
    private Label lbPhone;

    @FXML private ChoiceBox<Supplier.Type> cbType;

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

    private Supplier newSupplier = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Supplier.Type> observableList = FXCollections.observableList(Arrays.asList(Supplier.Type.values()));
        cbType.setItems(observableList);
    }

    public void btConfirmActionHandler(ActionEvent event) {

        if (newSupplier != null) {
            SupplierManager manager = new SupplierManager();
            newSupplier.setName(tfName.getText());
            newSupplier.setDate(dpData.getValue());
            newSupplier.setCpf(tfCPF.getText());
            if (newSupplier.getAdresses().get(0) != null) {
                newSupplier.getAdresses().get(0).setCountry(tfCountry.getText());
                newSupplier.getAdresses().get(0).setState(tfState.getText());
                newSupplier.getAdresses().get(0).setCity(tfCity.getText());
                newSupplier.getAdresses().get(0).setStreet(tfStreet.getText());
                newSupplier.getAdresses().get(0).setNumber(tfNumber.getText());
                newSupplier.getAdresses().get(0).setComp(tfComp.getText());
                newSupplier.getAdresses().get(0).setZip(tfZip.getText());
            }
            manager.register(newSupplier);
            return;
        }

        String name = tfName.getText(), cpf = tfCPF.getText(), phone = tfPhone.getText(),
                country = tfCountry.getText(), state = tfState.getText(), city = tfCity.getText(), street = tfStreet.getText(), number = tfNumber.getText(), comp = tfComp.getText(), zip = tfZip.getText();

        Person.Type type = cbType.getSelectionModel().getSelectedItem();

        LocalDate date = dpData.getValue();
        Adress adress = new Adress(country, state, city, street, number, comp, zip);

        Supplier supAlvo = new Supplier(type, name, cpf, date);
        supAlvo.addAdress(adress);
        supAlvo.addPhone(phone);
        SupplierManager supManager = new SupplierManager();
        supManager.register(supAlvo);

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void btCancelActionHandler(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void initData(Supplier supplier) {
        this.newSupplier = supplier;
        tfName.setText(newSupplier.getName());
        tfCPF.setText(newSupplier.getCpf());
        tfPhone.setText(newSupplier.getPhones().get(0));
        tfCountry.setText(newSupplier.getAdresses().get(0).getCountry());
        tfState.setText(newSupplier.getAdresses().get(0).getState());
        tfCity.setText(newSupplier.getAdresses().get(0).getCity());
        tfStreet.setText(newSupplier.getAdresses().get(0).getStreet());
        tfNumber.setText(newSupplier.getAdresses().get(0).getNumber());
        tfComp.setText(newSupplier.getAdresses().get(0).getComp());
        tfZip.setText(newSupplier.getAdresses().get(0).getZip());
    }
}
