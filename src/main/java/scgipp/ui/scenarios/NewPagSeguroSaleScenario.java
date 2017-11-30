package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.*;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.TransactionSearchService;
import com.sun.deploy.uitoolkit.impl.text.FXAppContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.boot.jaxb.SourceType;
import scgipp.Main;
import scgipp.data.webservice.CorreiosServer;
import scgipp.service.entities.CartItem;
import scgipp.service.entities.Product;
import scgipp.service.entities.Sale;
import scgipp.service.entities.SaleProduct;
import scgipp.service.managers.CartItemManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.SaleManager;
import scgipp.service.validators.CEP.CepData;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableCartItem;
import scgipp.ui.visible.ObservableProduct;
import scgipp.ui.visible.ObservableSale;
import scgipp.ui.visible.ObservableTransactionSummary;
import javax.mail.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.uol.pagseguro.PagSeguroTestSeller;

/**
 * User: hugo_<br/>
 * Date: 29/11/2017<br/>
 * Time: 01:00<br/>
 */
public class NewPagSeguroSaleScenario extends Scenario {

    private ProductManager productManager = ProductManager.getInstance();

    @FXML
    private HBox menuBar;

    @FXML
    private Button btExit;

    @FXML
    private ListView<ObservableCartItem> lvCartItens;

    @FXML
    private Button btAdd;

    @FXML
    private Button btRemove;

    @FXML
    private ComboBox<ShippingType> cbShippingType;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfState;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfDistrict;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfCEP;

    @FXML
    private TextField tfComplement;

    @FXML
    private TextField tfFreight;

    @FXML
    private Button btConfirm;

    @FXML
    private Button btCancel;

    @FXML
    private Button btFreight;

    @FXML
    private Label lbTotal;

    @FXML
    private ProgressIndicator piProgress;

    @FXML private ObservableList<ObservableCartItem> cartItemObservableList;

    public NewPagSeguroSaleScenario() {
        super("fxml/scenario_new_pagseguro.fxml");
    }

    private void setTotal(){
        Double total = 0.0;
        for(ObservableCartItem o: cartItemObservableList){
            total += o.getCartItem().getPrice();
        }
        total += Double.parseDouble(tfFreight.getText());
        lbTotal.setText("R$" + total);
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {

        cartItemObservableList = FXCollections.observableArrayList();
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, null, null);
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);

        ObservableList<ShippingType> observableList = FXCollections.observableArrayList(ShippingType.values());
        observableList.remove(ShippingType.NOT_SPECIFIED);
        cbShippingType.setItems(observableList);

        btFreight.setOnAction(event -> {
            if (!(tfCountry.getText().isEmpty() || tfState.getText().isEmpty() || tfCity.getText().isEmpty() || tfDistrict.getText().isEmpty() ||
                    tfStreet.getText().isEmpty() || tfNumber.getText().isEmpty() || tfCEP.getText().isEmpty())) {
                new Thread(new CalculateFreightTask()).start();
            }
            setTotal();
        });

        btCancel.setOnAction(event -> {
            for(ObservableCartItem o: lvCartItens.getItems()){
                o.getCartItem().getProduct().setQuantity(o.getCartItem().getProduct().getQuantity() + o.getCartItem().getQuantity());
                productManager.updateProduct(o.getCartItem().getProduct());
            }
            finish();
        });

        tfCEP.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 8){
                try {
                    tfCity.setText(CepData.get().getCidade(newValue));
                    tfState.setText(CepData.get().getUF(newValue));
                    tfDistrict.setText(CepData.get().getBairro(newValue));
                    tfStreet.setText(CepData.get().getRua(newValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btAdd.setOnAction(event -> {

            FeedbackScenario addCartPagSeguro = new AddCartPagSeguro();
            Spawner.startFeedbackScenario(addCartPagSeguro, 0, this, ((requestCode, resultCode, data) -> {
                Boolean setItemCart = true;
                CartItem cartItem = (CartItem) data.get(AddCartPagSeguro.FEEDBACK_NEW_CART_PRODUCT);
                for(ObservableCartItem Oitem: cartItemObservableList){
                    CartItem item = Oitem.getCartItem();
                    if(cartItem.getProduct().getName().equals(item.getProduct().getName())){
                        item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                        item.setPrice(item.getPrice() + cartItem.getPrice());
                        setItemCart = false;
                        break;
                    }
                }
                if(setItemCart){
                    cartItemObservableList.add(new ObservableCartItem(cartItem));
                }
                lvCartItens.setItems(cartItemObservableList);
                lvCartItens.refresh();
            }));
        });

        btConfirm.setOnAction(event -> {

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setCurrency(Currency.BRL);

            Integer totalItens = 0;
            BigDecimal frete = new BigDecimal(Double.parseDouble(tfFreight.getText()));

            List<ObservableCartItem> cartItemList = lvCartItens.getItems();

            for(ObservableCartItem o: cartItemList){
                totalItens += o.getCartItem().getQuantity();
            }

            frete = new BigDecimal(frete.doubleValue()/totalItens).setScale(2, BigDecimal.ROUND_UP);
            System.out.println("Frete: " + frete);

            for(ObservableCartItem o: cartItemList){
                CartItem cartItem = o.getCartItem();
                paymentRequest.addItem(cartItem.getProduct().getId().toString(),
                        cartItem.getProduct().getDescription(),
                        cartItem.getQuantity(),
                        cartItem.getProduct().getAmount(),
                        cartItem.getProduct().getWeight(),
                        frete);
            }

            Shipping shipping = new Shipping();
            shipping.setType(cbShippingType.getSelectionModel().getSelectedItem());
            shipping.setAddress(new Address(tfCountry.getText(), tfState.getText(),
                    tfCity.getText(), tfDistrict.getText(),
                    tfCEP.getText(), tfStreet.getText(), tfNumber.getText(),
                    tfComplement.getText()));

            paymentRequest.setShipping(shipping);

            try{
                URL paymentURL = new URL(paymentRequest.register(new AccountCredentials(
                        PagSeguroTestSeller.email,
                        PagSeguroTestSeller.productionToken,
                        PagSeguroTestSeller.sandboxToken)));
                System.out.println("paymentURL: " + paymentURL);
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            } catch (PagSeguroServiceException e) {
                e.printStackTrace();
            }

            finish();
        });

        btRemove.setOnAction(event -> {
            ObservableCartItem observableCartItem = lvCartItens.getSelectionModel().getSelectedItem();
            observableCartItem.getCartItem().getProduct().setQuantity(observableCartItem.getCartItem().getProduct().getQuantity() + observableCartItem.getCartItem().getQuantity());
            productManager.updateProduct(observableCartItem.getCartItem().getProduct());
            cartItemObservableList.remove(observableCartItem);
            setTotal();
            lvCartItens.refresh();
        });

    }

    protected class CalculateFreightTask extends Task<BigDecimal> {

        @Override
        protected BigDecimal call() throws Exception {

            btFreight.setVisible(false);
            piProgress.setVisible(true);

            String serviceCode;
            if (cbShippingType.getSelectionModel().getSelectedItem().equals(ShippingType.PAC))
                serviceCode = CorreiosServer.ServiceCode.PAC_VAREJO;
            else serviceCode = CorreiosServer.ServiceCode.SEDEX_VAREJO;

            final String originCep = "87200424";

            long weight = 0;
            double value = 0;
            for (ObservableCartItem oItem : lvCartItens.getItems()) {
                CartItem item = oItem.getCartItem();
                weight += (item.getQuantity() * item.getProduct().getWeight());
                value += item.getPrice();
            }
            System.out.println("Weight: " + weight);
            System.out.println("Value: " + value);
            System.out.println("Cep: " + tfCEP.getText());
            System.out.println("Code: " + serviceCode);

            CorreiosServer correiosServer = new CorreiosServer();
            String ret = correiosServer.calcFreightSimulation(null, null,
                    serviceCode, originCep, tfCEP.getText(),
                    String.valueOf(weight), CorreiosServer.PackageType.BOX_PACKAGE,
                    50,50,50,20,
                    false, value, false)
                    .getProperty(CorreiosServer.PropertyTags.VALUE).replace(',', '.');
            return new BigDecimal(ret);

        }

        @Override
        protected void succeeded() {
            btFreight.setVisible(true);
            piProgress.setVisible(false);
            tfFreight.setText(getValue().toString());
        }

        @Override
        protected void failed() {
            getException().printStackTrace();
        }
    }

}
