package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;
import scgipp.service.sale_management.SaleBudget;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.transportadora_management.TransportadoraManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellUIController implements Initializable {

    @FXML private ListView<Product> avaliableList;
    @FXML private ListView<Product> productList;
    @FXML private ComboBox<Transportadora> cbTransp;
    @FXML private TextField tfTotal;
    @FXML private TextField tfFreight;
    @FXML private TextField tfDesconto;
    @FXML private TextField tfTotalDesconto;
    @FXML private ComboBox<String> cbPgmto;

    private List<Product> aList = null;
    private List<Transportadora> tList = null;
    private List<String> pList = new ArrayList<>();
    private SaleBudget saleBudget = new SaleBudget();

    ObservableList<Product> oProductList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pList.add("Crédito");
        pList.add("Boleto");
        pList.add("Transferência Bancária");

        ObservableList<String> obPgmto = FXCollections.observableList(pList);
        cbPgmto.setItems(obPgmto);

        TransportadoraManager transportadoraManager = new TransportadoraManager();
        tList = transportadoraManager.getAll();

        ObservableList<Transportadora> oTranspList = FXCollections.observableArrayList(tList);
        cbTransp.setItems(oTranspList);
        cbTransp.setCellFactory(param -> new ListCell<Transportadora>() {
            @Override
            protected void updateItem(Transportadora item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }

                saleBudget.trasporter = item;
                tfFreight.setText("R$" + saleBudget.freight());

            }
        });

        ProductManager productManager = new ProductManager();
        aList = productManager.getAll();

        ObservableList<Product> oAvaliableList = FXCollections.observableArrayList(aList);
        avaliableList.setItems(oAvaliableList);
        avaliableList.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (true);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        oProductList = FXCollections.observableArrayList(saleBudget.products);
        productList.setItems(oProductList);
        productList.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

    }

    public void btExitActionHandler(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void btSellBudgetActionHandler(ActionEvent event) {

    }

    public void btSellActionHandler(ActionEvent event) {

    }

    public void btRemoveProductActionHandler(ActionEvent event) {
        saleBudget.products.remove(productList.getSelectionModel().getSelectedItem());
        oProductList = FXCollections.observableArrayList(saleBudget.products);
        productList.setItems(oProductList);
        tfTotal.setText("R$" + saleBudget.totalPrice());
    }

    public void btAddProductActionHandler(ActionEvent event) {
        saleBudget.products.add(avaliableList.getSelectionModel().getSelectedItem());
        oProductList = FXCollections.observableArrayList(saleBudget.products);
        productList.setItems(oProductList);
        tfTotal.setText("" + saleBudget.totalPrice());
    }

    public void tfDescontoActionHandler(ActionEvent event) {

        int desconto = Integer.valueOf(tfDesconto.getText());
        saleBudget.multiplier = 1 - (desconto/100);
        tfTotalDesconto.setText("R$" + saleBudget.totalPrice() * saleBudget.multiplier);

    }

}
