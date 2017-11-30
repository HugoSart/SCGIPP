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
import scgipp.service.managers.CartItemManager;
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

public class AddCartPagSeguro extends FeedbackScenario { //para o zé

    private ProductManager productManager = ProductManager.getInstance();
    private CartItemManager cartItemManager = CartItemManager.getInstance();

    public static final String FEEDBACK_NEW_CART_PRODUCT = "new_cart_product";

    @FXML private TableView<ObservableProduct> tvItem;
    @FXML private TableColumn<ObservableProduct, String> tcItem;
    @FXML private TableColumn<ObservableProduct, Double> tcPrice;
    @FXML private TableColumn<ObservableProduct, Integer> tcQuantity;
    @FXML private Button btAdd;
    @FXML private Label lbQuantity;
    @FXML private Label lbMaxStock;
    @FXML private TextField tfSearch;
    @FXML private Spinner<Integer> spQuantity;
    @FXML private ObservableList<ObservableProduct> productObservableList;

    public AddCartPagSeguro() {
        super("fxml/scenario_add_cart_pagseguro.fxml");
    }


    @Override
    protected void onConfigStage(Stage stage) {

            List<Product> productList = productManager.listAll();
            List<CartItem> cartItemList = new ArrayList<>();

            btAdd.setOnAction(((ActionEvent event) -> {

                Integer numberItens = spQuantity.getValue();
                ObservableProduct observableProduct = tvItem.getSelectionModel().getSelectedItem();

                lbMaxStock.setVisible(numberItens > observableProduct.getProduct().getQuantity());
                if (numberItens <= observableProduct.getProduct().getQuantity())
                {

                    Double totalAmount = numberItens * observableProduct.getProduct().getAmount().doubleValue();
                    Integer qtd = observableProduct.getProduct().getQuantity();
                    observableProduct.getProduct().setQuantity(qtd - numberItens);
                    productManager.updateProduct(observableProduct.getProduct());

                    SpinnerValueFactory<Integer> valueFactory = //
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                                    1000,
                                    1);
                    spQuantity.setValueFactory(valueFactory);

                    CartItem cartItem = new CartItem(observableProduct.getProduct(), numberItens, totalAmount);
                    putFeedback(FEEDBACK_NEW_CART_PRODUCT, cartItem);
                    processFeedbackAndFinish();
                }

            }));

            productObservableList = FXCollections.observableList(ObservableProduct.productListTAsObservableProductList(productList));

            tcItem.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            tcQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
            tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
            tvItem.setItems(productObservableList);


            SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                        1000,
                        1);
            spQuantity.setValueFactory(valueFactory);

            FilteredList<ObservableProduct> filteredDataProduct = new FilteredList<>(productObservableList, p -> true);
            tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredDataProduct.setPredicate(myObject -> {
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
