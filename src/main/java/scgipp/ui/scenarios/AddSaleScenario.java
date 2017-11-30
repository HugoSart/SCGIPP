package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.UserSession;
import scgipp.service.entities.*;
import scgipp.service.managers.CustomerManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableCustomer;
import scgipp.ui.visible.ObservableProduct;
import scgipp.ui.visible.ObservableSale;
import scgipp.ui.visible.ObservableSaleProduct;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;

public class AddSaleScenario extends FeedbackScenario {

    private UserManager userManager = UserManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance();
    private UserSession userSession = UserSession.getSession();
    private DBManager dbManager = new DBConnection().manager();

    public static final String FEEDBACK_NEW_SALE = "new_sale";


    @FXML
    private TextField tfCustomerAddress;

    @FXML
    private Label lbCNPJ_CPF;

    @FXML
    private TextField tfCNPJ_CPF;

    @FXML
    private Label lbFone;

    @FXML
    private TextField tfPhone;

    @FXML
    private Label lbCliente;

    @FXML
    private TableView<ObservableCustomer> tvCustomer;

    @FXML
    private TableColumn<ObservableCustomer, String> tcCustomerName;

    @FXML
    ObservableList<ObservableCustomer> customerObservableList;

    @FXML
    private Label lbVendedorName;

    @FXML
    private TextField tfSalesmanName;

    @FXML
    private Button btSelecionar;

    @FXML
    private TableView<ObservableProduct> tvItem;

    @FXML
    private TableColumn<ObservableProduct, String> tcItem;

    @FXML
    private TableColumn<ObservableProduct, Double> tcPrice;

    @FXML private ObservableList<ObservableProduct> productObservableList;

    @FXML private ObservableList<ObservableSaleProduct> productObservableSaleList;

    @FXML
    private TableView<ObservableSaleProduct> tvItemList;

    @FXML
    private TableColumn<ObservableSaleProduct, String> tcItemListName;

    @FXML
    private TableColumn<ObservableSaleProduct, Integer> tcItemListUnity;

    @FXML
    private TableColumn<ObservableSaleProduct, Double > tcItemListPrice;

    @FXML
    private Button btRemove;

    @FXML
    private Button btAdd;

    @FXML
    private TextField tfPesquisar;

    private List<SaleProduct> itensToSale;

    @FXML
    private Spinner<Integer> spQuantity;

    @FXML
    private Label lbQuantidade;

    @FXML
    private TextField tfPesquisarCliente;


    @FXML
    private Label lbTotalMsg;

    @FXML
    private Label lbtTotalPriceSale;

    @FXML
    private Button btFinishSale;

    private Double totalAmount = 0.0;

    private Customer clienteFinal;

    @FXML
    private Label lbClienteEmpty;


    @FXML
    private Button btNewSale;

    @FXML
    private Button btRemoveSale;


    public AddSaleScenario() {
        super("fxml/scenario_add_sale.fxml");
    }


    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {


        if (itensToSale == null) itensToSale = new ArrayList<>();

        tfCNPJ_CPF.setDisable(true);
        tfCustomerAddress.setDisable(true);
        tfPhone.setDisable(true);
        tfSalesmanName.setDisable(true);
        tfSalesmanName.setText(userSession.getActiveUser().getLogin());

        List<Customer> customerList = customerManager.getAll();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        customerObservableList = FXCollections.observableList(ObservableCustomer.custumerListTAsObservableUserList(customerList));
        tcCustomerName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tvCustomer.setItems(customerObservableList);

        List<Product> productList = productManager.listAll();
        for (Product product : productList) {
            System.out.println(product);


            btSelecionar.setOnAction(event -> {
                ObservableCustomer customer = tvCustomer.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    tfPhone.setText(customer.getCustomer().getPhones().get(0).getNumber());
                    tfCustomerAddress.setText(customer.getCustomer().getAddresses().get(0).getStreet() + " n° " + customer.getCustomer().getAddresses().get(0).getNumber());
                    tfCNPJ_CPF.setText(customer.getCustomer().getCpf_cnpj());
                    clienteFinal = customer.getCustomer();
                }
            });

            btRemove.setOnAction(event -> {
                System.out.println(itensToSale.size());
                ObservableSaleProduct observableSaleProduct = tvItemList.getSelectionModel().getSelectedItem();
                dbManager.remove(observableSaleProduct.getSaleProduct());
                itensToSale.remove(observableSaleProduct.getSaleProduct());
                System.out.println(itensToSale.size());
                productObservableSaleList = FXCollections.observableList(ObservableSaleProduct.productListTAsObservableSaleProductList(itensToSale));
                tcItemListName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                tcItemListPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
                tvItemList.setItems(productObservableSaleList);
                tvItemList.refresh();
                totalAmount -= observableSaleProduct.getSaleProduct().getProduct().getAmount().doubleValue() * observableSaleProduct.getSaleProduct().getQuantity();
                lbtTotalPriceSale.setText(String.valueOf(totalAmount));
                tvItemList.refresh();

            });

            btFinishSale.setOnAction(event -> {

                lbClienteEmpty.setVisible(clienteFinal == null);

                if (clienteFinal != null)
                {
                    Sale newSale = new Sale(userSession.getActiveUser(), clienteFinal, "SALE", itensToSale);
                    BigDecimal b = new BigDecimal(totalAmount, MathContext.DECIMAL64);
                    newSale.setTotalPrice(b);
                    putFeedback(FEEDBACK_NEW_SALE, newSale);
                    processFeedbackAndFinish();
                }
            });


            btAdd.setOnAction(((ActionEvent event) -> {
                Integer numberItens = spQuantity.getValue();
                ObservableProduct observableSaleProduct = tvItem.getSelectionModel().getSelectedItem();
                /*
                Product novoProduto = new Product(observableSaleProduct.getProduct().getName(),
                        observableSaleProduct.getProduct().getDescription(),
                        numberItens,
                        observableSaleProduct.getProduct().getAmount(),
                        observableSaleProduct.getProduct().getWeight());
                */
                SaleProduct nSP = new SaleProduct(numberItens, observableSaleProduct.getProduct());
                itensToSale.add(nSP);
                dbManager.add(nSP);
                productObservableSaleList.add(new ObservableSaleProduct(nSP));
                System.out.println(numberItens);
                tvItemList.refresh();
                totalAmount += numberItens * observableSaleProduct.getProduct().getAmount().doubleValue();
                lbtTotalPriceSale.setText(String.valueOf(totalAmount));
                SpinnerValueFactory<Integer> valueFactory = //
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                                1000,
                                1);
                spQuantity.setValueFactory(valueFactory);
            }));

            lbClienteEmpty.setVisible(false);
            FilteredList<ObservableCustomer> filteredData = new FilteredList<>(customerObservableList, p -> true);
            tfPesquisarCliente.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getCustomer().getName()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (String.valueOf(myObject.getCustomer().getId()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;
            }));
            tvCustomer.setItems(filteredData);

            productObservableList = FXCollections.observableList(ObservableProduct.productListTAsObservableProductList(productList));
            productObservableSaleList = FXCollections.observableList(ObservableSaleProduct.productListTAsObservableSaleProductList(itensToSale));

            tcItem.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
            tvItem.setItems(productObservableList);


            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                            1000,
                            1);
            spQuantity.setValueFactory(valueFactory);

            tcItemListName.setCellValueFactory(param -> param.getValue().nameProperty());
            tcItemListUnity.setCellValueFactory(param -> param.getValue().quantityProperty().asObject());
            tcItemListPrice.setCellValueFactory(param -> param.getValue().totalPriceProperty().asObject());
            tvItemList.setItems(productObservableSaleList);
            tvItemList.refresh();

            FilteredList<ObservableProduct> filteredDataProduct = new FilteredList<>(productObservableList, p -> true);
            tfPesquisar.textProperty().addListener((observable, oldValue, newValue) -> filteredDataProduct.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getProduct().getName()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (String.valueOf(myObject.getProduct().getId()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;
            }));
            tvItem.setItems(filteredDataProduct);


        }
    }

}
