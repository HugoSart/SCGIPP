package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Shipping;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
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
import scgipp.Main;
import scgipp.data.webservice.CorreiosServer;
import scgipp.service.entities.Product;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.visible.ObservableTransactionSummary;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 29/11/2017<br/>
 * Time: 01:00<br/>
 */
public class NewPagSeguroSaleScenario extends Scenario {

    @FXML
    private HBox menuBar;

    @FXML
    private Button btExit;

    @FXML
    private ListView<Product> lvProducts;

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

    public NewPagSeguroSaleScenario() {
        super("fxml/scenario_new_pagseguro.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {

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
        });

        btCancel.setOnAction(event -> {
            finish();
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
            BigDecimal value = new BigDecimal(0);
            for (Product p : lvProducts.getItems()) {
                weight += p.getWeight();
                value = value.add(p.getAmount());
            }

            CorreiosServer correiosServer = new CorreiosServer();
            String ret = correiosServer.calcFreightSimulation(null, null, serviceCode, originCep, tfCEP.getText(),
                    String.valueOf(weight), CorreiosServer.PackageType.BOX_PACKAGE, 100,100,100,100,
                    false, value.doubleValue(), false).getProperty(CorreiosServer.PropertyTags.VALUE).replace(',', '.');
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
