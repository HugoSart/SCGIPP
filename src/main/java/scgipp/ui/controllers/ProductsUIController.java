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
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.ProductAddUIManager;
import scgipp.ui.manager.ProductInfoUIManager;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class ProductsUIController implements Initializable {

    @FXML private TextField tfSearch;

    @FXML private TableView<Product> tvProducts;
    @FXML private TableColumn<Product, Integer> tcId;
    @FXML private TableColumn<Product, Integer> tcType;
    @FXML private TableColumn<Product, String> tcName;
    @FXML private TableColumn<Product, Integer> tcAmount;
    @FXML private TableColumn<Product, Double> tcPrice;
    @FXML private TableColumn<Product, String> tcDescription;

    private ProductManager productManager;
    private ObservableList<Product> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productManager = new ProductManager();
        observableList = FXCollections.observableList(productManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tvProducts.setItems(observableList);

        tvProducts.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();
                    ProductAddUIManager manager = new ProductAddUIManager(rowData);
                    Stage stage = manager.newStage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            });
            return row ;
        });

    }

    @FXML
    public void btAddActionHandler(ActionEvent event) {
        ProductAddUIManager manager = new ProductAddUIManager();
        Stage stage = manager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Novo Produto");
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().focusedProperty().addListener((observable, oldValue, newValue) -> updateTable());
    }

    @FXML
    public void btRemoveActionHandler(ActionEvent event) {
        (new ProductManager()).remove(tvProducts.getSelectionModel().getSelectedItem());
        updateTable();
    }

    @FXML
    public void btCloseActionHandler(ActionEvent event) {
        hide();
    }

    private void updateTable() {
        observableList = FXCollections.observableList(productManager.getAll());
        tvProducts.setItems(observableList);
        tvProducts.refresh();
    }

    private void hide() {
        tfSearch.getParent().getScene().getWindow().hide();
    }

}
