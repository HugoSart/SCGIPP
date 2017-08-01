package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import scgipp.service.sale_management.Sale;
import scgipp.service.sale_management.SaleBudget;
import scgipp.service.sale_management.SaleBudgetManager;
import scgipp.service.sale_management.SaleManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class SaleInfoUIController implements Initializable {

    @FXML private TextField tfSearch;

    @FXML private AnchorPane mainPane;

    @FXML private TableView<Sale> tvSales;
    @FXML private TableColumn<Sale, Integer> tcId;
    @FXML private TableColumn<Sale, String> tcName;
    @FXML private TableColumn<Sale, String> tcDate;
    @FXML private TableColumn<Sale, String> tcStatus;

    private SaleManager saleManager = new SaleManager();
    private ObservableList<Sale> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        observableList = FXCollections.observableList(saleManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tvSales.setItems(observableList);

    }


    @FXML
    public void btConfirmActionHandler(ActionEvent event) {
        saleManager.confirmSale(tvSales.getSelectionModel().getSelectedItem());
        updateTable();
    }

    @FXML
    public void btRemoveActionHandler(ActionEvent event) {

    }

    @FXML
    public void btCloseActionHandler(ActionEvent event) {
        hide();
    }

    private void updateTable() {
        observableList = FXCollections.observableList(saleManager.getAll());
        tvSales.setItems(observableList);
        tvSales.refresh();
    }

    private void hide() {
        tfSearch.getParent().getScene().getWindow().hide();
    }

}
