package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scgipp.service.entities.Customer;
import scgipp.service.entities.Product;
import scgipp.service.managers.CustomerManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableCustomer;
import scgipp.ui.visible.ObservableProduct;

import java.math.BigDecimal;
import java.util.List;

public class AddSaleScenario extends FeedbackScenario {

    private UserManager userManager = UserManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance();


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

    @FXML
    private Button btAdd;

    @FXML private ObservableList<ObservableProduct> productObservableList;



    public AddSaleScenario() {
        super("fxml/scenario_add_sale.fxml");
    }


    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {
        tfCNPJ_CPF.setDisable(true);
        tfCustomerAddress.setDisable(true);
        tfPhone.setDisable(true);
        tfSalesmanName.setDisable(true);
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
        }

        productObservableList = FXCollections.observableList(ObservableProduct.productListTAsObservableProductList(productList));
        tcItem.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tvItem.setItems(productObservableList);


        btSelecionar.setOnAction(event -> {
            ObservableCustomer customer = tvCustomer.getSelectionModel().getSelectedItem();
            if(customer != null)
            {
                tfPhone.setText(customer.getCustomer().getPhones().get(0).getNumber());
                tfCustomerAddress.setText(customer.getCustomer().getAddresses().get(0).getStreet());
                tfCNPJ_CPF.setText(customer.getCustomer().getCpf_cnpj());
            }
        });


    }

}
