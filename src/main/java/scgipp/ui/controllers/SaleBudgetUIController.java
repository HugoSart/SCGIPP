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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;
import scgipp.service.sale_management.Sale;
import scgipp.service.sale_management.SaleBudget;
import scgipp.service.sale_management.SaleBudgetManager;
import scgipp.ui.manager.ProductAddUIManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class SaleBudgetUIController implements Initializable {

    @FXML private TextField tfSearch;

    @FXML private TableView<SaleBudget> tvProducts;
    @FXML private TableColumn<SaleBudget, Integer> tcId;
    @FXML private TableColumn<SaleBudget, String> tcName;
    @FXML private TableColumn<SaleBudget, String> tcProducts;

    private SaleBudgetManager saleBudgetManager;
    private ObservableList<SaleBudget> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saleBudgetManager = new SaleBudgetManager();
        observableList = FXCollections.observableList(saleBudgetManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcProducts.setCellValueFactory(new PropertyValueFactory<>("products"));

        tvProducts.setItems(observableList);

        tvProducts.setRowFactory( tv -> {
            TableRow<SaleBudget> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {

                }
            });
            return row ;
        });

    }


    @FXML
    public void btRemoveActionHandler(ActionEvent event) {
        saleBudgetManager.removeBudget(tvProducts.getSelectionModel().getSelectedItem().getId());
        updateTable();
    }

    @FXML
    public void btCloseActionHandler(ActionEvent event) {
        hide();
    }

    private void updateTable() {
        observableList = FXCollections.observableList(saleBudgetManager.getAll());
        tvProducts.setItems(observableList);
        tvProducts.refresh();
    }

    private void hide() {
        tfSearch.getParent().getScene().getWindow().hide();
    }

}
