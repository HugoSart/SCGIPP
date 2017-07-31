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
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.transportadora_management.TransportadoraManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import static scgipp.service.Person.*;

/**
 * Created by kira on 07/06/17.
 */
public class AddTransportadoraUIController implements Initializable{
    @FXML
    private Label lbPhone;

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

    private Transportadora newTransp = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> observableList = FXCollections.observableList(Arrays.asList(Type.LEGAL));
        cbType.setItems(observableList);
    }

    public void btConfirmActionHandler(ActionEvent event) {

        if (newTransp != null) {
            TransportadoraManager manager = new TransportadoraManager();
            newTransp.setName(tfName.getText());
            newTransp.setDate(dpData.getValue());
            newTransp.setCpf(tfCPF.getText());
            if (newTransp.getAddresses().get(0) != null) {
                newTransp.getAddresses().get(0).setCountry(tfCountry.getText());
                newTransp.getAddresses().get(0).setState(tfState.getText());
                newTransp.getAddresses().get(0).setCity(tfCity.getText());
                newTransp.getAddresses().get(0).setStreet(tfStreet.getText());
                newTransp.getAddresses().get(0).setNumber(tfNumber.getText());
                newTransp.getAddresses().get(0).setComp(tfComp.getText());
                newTransp.getAddresses().get(0).setZip(tfZip.getText());
            }
            manager.update(newTransp);
            return;
        }

        String name = tfName.getText(), cpf = tfCPF.getText(), phone = tfPhone.getText(),
                country = tfCountry.getText(), state = tfState.getText(), city = tfCity.getText(), street = tfStreet.getText(), number = tfNumber.getText(), comp = tfComp.getText(), zip = tfZip.getText();

        Type type = cbType.getSelectionModel().getSelectedItem();

        LocalDate date = dpData.getValue();
        Address address = new Address(country, state, city, street, number, comp, zip);

        Transportadora transpAlvo = new Transportadora(cpf, name, date, null, null);
        transpAlvo.addAdress(address);
        transpAlvo.addPhone(phone);
        TransportadoraManager transpManager = new TransportadoraManager();
        transpManager.register(cpf, name, date, phone, address);

                ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void btCancelActionHandler(ActionEvent event) {

        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void initData(Transportadora transportadora) {
        this.newTransp = transportadora;
        if (transportadora != null) {
                tfName.setText(newTransp.getName());
            tfCPF.setText(newTransp.getCpf());
            tfPhone.setText(newTransp.getPhones().get(0));
            tfCountry.setText(newTransp.getAddresses().get(0).getCountry());
            tfState.setText(newTransp.getAddresses().get(0).getState());
            tfCity.setText(newTransp.getAddresses().get(0).getCity());
            tfStreet.setText(newTransp.getAddresses().get(0).getStreet());
            tfNumber.setText(newTransp.getAddresses().get(0).getNumber());
            tfComp.setText(newTransp.getAddresses().get(0).getComp());
            tfZip.setText(newTransp.getAddresses().get(0).getZip());
        }
    }


    public void btAddActionHandler(ActionEvent actionEvent) {
        String name = tfName.getText(), cpf = tfCPF.getText(), phone = tfPhone.getText(),
                country = tfCountry.getText(), state = tfState.getText(), city = tfCity.getText(), street = tfStreet.getText(), number = tfNumber.getText(), comp = tfComp.getText(), zip = tfZip.getText();

        Type type = cbType.getSelectionModel().getSelectedItem();

        LocalDate date = dpData.getValue();
        Address address = new Address(country, state, city, street, number, comp, zip);

        TransportadoraManager transpManager = new TransportadoraManager();

        if (newTransp != null) {
            newTransp.setCpf(cpf);
            newTransp.setDate(date);
            newTransp.setName(name);
            newTransp.addAdress(address);
            newTransp.addPhone(phone);
            newTransp.setType(type);
            transpManager.update(newTransp);
            hide();
            return;
        }

       transpManager.register(cpf, name, date, phone, address);;

        hide();
    }

    private void hide() {
        tfName.getParent().getScene().getWindow().hide();
    }
}
