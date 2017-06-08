package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.customer_management.Customer;
import scgipp.service.customer_management.CustomerManager;
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.AddCustomerUIManager;
import scgipp.ui.manager.CustomerInfoUIManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomersUIController implements Initializable {

    @FXML private TableView<Customer> tvCustomers;
    @FXML private TableColumn<Customer, Integer> tcId;
    @FXML private TableColumn<Customer, Customer.Type> tcType;
    @FXML private TableColumn<Customer, String> tcName;
    @FXML private TableColumn<Customer, LocalDate> tcDate;
    @FXML private TableColumn<Customer, String> tcCPF;

    CustomerManager customerManager = new CustomerManager();
    ObservableList<Customer> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initViews();

        customerManager = new CustomerManager();
        observableList = FXCollections.observableList(customerManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tvCustomers.setItems(observableList);

        tvCustomers.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Customer rowData = row.getItem();
                    CustomerInfoUIManager manager = new CustomerInfoUIManager(rowData);
                    Stage stage = manager.newStage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.show();
                }
            });
            return row ;
        });
    }

    public void initViews() {

    }

    public void btAddActionHandler(ActionEvent event) {
        AddCustomerUIManager addCustomerUIManager = new AddCustomerUIManager();
        Stage stage = addCustomerUIManager.newStage();
        stage.setTitle("Novo cliente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().focusedProperty().addListener((observable, oldValue, newValue) -> updateTable());
    }

    public void btRemoveActionHandler(ActionEvent event) {
        (new CustomerManager()).remove(tvCustomers.getSelectionModel().getSelectedItem());
        updateTable();
    }

    public void btCloseActionHandler(ActionEvent event) {

    }

    private void updateTable() {
        observableList = FXCollections.observableList(customerManager.getAll());
        tvCustomers.setItems(observableList);
        tvCustomers.refresh();
    }

}
