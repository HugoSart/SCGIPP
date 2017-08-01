package scgipp.ui.controllers;

import com.rits.cloning.Cloner;
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
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;
import scgipp.service.sale_management.SaleBudget;
import scgipp.service.sale_management.SaleBudgetManager;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.transportadora_management.TransportadoraManager;
import scgipp.ui.manager.DialogManager;
import scgipp.ui.manager.SaleBudgetUIManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SaleUIController implements Initializable {

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

        saleBudget.products = new ArrayList<>();

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
        SaleBudgetUIManager manager = new SaleBudgetUIManager();
        Stage stage = manager.newStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("ORÇAMENTOS");
        stage.show();
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
        tfTotal.setText("R$" + saleBudget.totalPrice());
    }

    public void tfCEPTextChangedHandler(InputMethodEvent event) {
        tfFreight.setText("R$" + saleBudget.freight());
    }

    public void tfDescontoActionHandler(ActionEvent event) {
        saleBudget.multiplier = 1.0 - (Double.valueOf(tfDesconto.getText())/100.0);
        System.out.println("multiplier: " + saleBudget.multiplier);
        tfTotalDesconto.setText("R$" + saleBudget.totalPrice() * saleBudget.multiplier);
    }

    public void btSaveActionHandler(ActionEvent event) {
        SaleBudgetManager saleBudgetManager = new SaleBudgetManager();
        SaleBudget clone = (new Cloner()).deepClone(saleBudget);
        clone.id = null;
        if (DialogManager.askBudgetName(clone)) {
            saleBudgetManager.saveBudget(clone);
        }
    }

}
