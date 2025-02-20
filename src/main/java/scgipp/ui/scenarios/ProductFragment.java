package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.Product;
import scgipp.service.entities.User;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableProduct;
import scgipp.ui.visible.ObservableUser;

import java.math.BigDecimal;
import java.util.List;

public class ProductFragment extends Fragment {

    private ProductManager productManager = ProductManager.getInstance();

    @FXML private TableView<ObservableProduct> tvProduct;
    @FXML private TableColumn<ObservableProduct, Integer> tcId;
    @FXML private TableColumn<ObservableProduct, String> tcName;
    @FXML private TableColumn<ObservableProduct, Integer> tcQuantity;
    @FXML private TableColumn<ObservableProduct, Double> tcPrice;
    @FXML private TableColumn<ObservableProduct, String> tcDescription;
    @FXML private TableColumn<ObservableProduct, Long> tcWeight;

    @FXML private TextField tfSearch;
    @FXML private Button btAddProduct;
    @FXML private Button btRemoveProduct;
    @FXML private Button btUpdateProduct;

    @FXML private ObservableList<ObservableProduct> productObservableList;

    public ProductFragment() {
        super("fxml/fragment_product.fxml");
    }

    @Override
    protected void onCreateView() {

        List<Product> productList = productManager.listAll();
        for (Product product : productList) {
            System.out.println(product);
        }

        productObservableList = FXCollections.observableList(ObservableProduct.productListTAsObservableProductList(productList));
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tcDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        tcWeight.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        tvProduct.setItems(productObservableList);

        btAddProduct.setOnAction(event -> {
            FeedbackScenario addProductScenario = new AddProductScenario();
            Spawner.startFeedbackScenario(addProductScenario, 0, this, (requestCode, resultCode, data) -> {
                Product product = (Product)data.get(AddProductScenario.FEEDBACK_NEW_PRODUCT);
                System.out.println("Product: " + product);
                System.out.println("Product manager: " + productManager);
                if (productManager.addProduct(product) != -1)
                    productObservableList.add(new ObservableProduct(product));
                tvProduct.refresh();

            });
        });

        btRemoveProduct.setOnAction(event -> {
            ObservableProduct observableProduct = tvProduct.getSelectionModel().getSelectedItem();
            ProductManager.removeProduct(observableProduct.getProduct());
            productObservableList.remove(observableProduct);
            tvProduct.refresh();
        });

        btUpdateProduct.setOnAction(event -> {
            ObservableProduct observableProduct = tvProduct.getSelectionModel().getSelectedItem();
            FeedbackScenario updateProductScenario = new UpdateProductScenario(observableProduct.getProduct());
            Spawner.startFeedbackScenario(updateProductScenario, 0, this, (requestCode, resultCode, data) -> {
                Product productUpdated = (Product)data.get(UpdateProductScenario.FEEDBACK_NEW_PRODUCT);
                productManager.updateProduct(productUpdated);
                tvProduct.refresh();
            });
        });

        tvProduct.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                ObservableProduct observableProduct = tvProduct.getSelectionModel().getSelectedItem();
            }
        });

        FilteredList<ObservableProduct> filteredData = new FilteredList<>(productObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getProduct().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getProduct().getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvProduct.setItems(filteredData);

    }




}
