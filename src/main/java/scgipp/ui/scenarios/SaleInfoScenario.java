package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.TransactionSearchService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.Main;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.visible.ObservableTransactionSummary;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * User: hugo_<br/>
 * Date: 25/11/2017<br/>
 * Time: 17:05<br/>
 */
public class SaleInfoScenario extends Scenario {

    @FXML private AnchorPane pInfo;
    @FXML private HBox menuBar;
    @FXML private Label lbSessionUser1;
    @FXML private Button btExit;

    @FXML private VBox vbSearching;

    @FXML private Label lbCode;
    @FXML private Label lbTransactionDate;
    @FXML private Label lbType;
    @FXML private Label lbLastUpdate;
    @FXML private Label lbPaymentType;
    @FXML private Label lbGrossAmount;
    @FXML private Label lbDisccountAmount;
    @FXML private Label lbFeeAmount;
    @FXML private Label lbExtraAmount;
    @FXML private Label lbNetAmount;
    @FXML private Label lbStatus;

    @FXML private TableView<Item> tvProducts;
    @FXML private TableColumn<Item, String> tcName;
    @FXML private TableColumn<Item, Double> tcAmount;
    @FXML private TableColumn<Item, Long> tcWeight;
    @FXML private TableColumn<Item, Integer> tcQuantity;
    @FXML private TableColumn<Item, Double> tcShippingCost;

    private Transaction transaction;
    private ObservableList<Item> observableList;

    public SaleInfoScenario() {
        super("fxml/scenario_sale_info.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, null, null);
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
        new Thread(new SaleInfoScenario.LoadTransactionTask()).start();
    }

    protected class LoadTransactionTask extends Task<Transaction> {

        @Override
        protected Transaction call() throws Exception {

            pInfo.setVisible(false);
            vbSearching.setVisible(true);
            return TransactionSearchService.searchByCode(Main.getCredentials(), ((TransactionSummary)getExtra("summary")).getCode());

        }

        @Override
        protected void succeeded() {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            transaction = getValue();

            vbSearching.setVisible(false);
            pInfo.setVisible(true);

            lbCode.setText(transaction.getCode());
            lbTransactionDate.setText(sdf.format(transaction.getDate()));
            lbType.setText(transaction.getType().getDescription());
            lbPaymentType.setText(transaction.getPaymentMethod().getType().name());
            lbGrossAmount.setText("R$" + transaction.getGrossAmount().toString());
            lbDisccountAmount.setText("R$" + transaction.getDiscountAmount().toString());
            lbFeeAmount.setText("R$" + transaction.getFeeAmount());
            lbExtraAmount.setText("R$" + transaction.getExtraAmount());
            lbNetAmount.setText("R$" + transaction.getNetAmount());
            lbStatus.setText(transaction.getStatus().name());
            lbLastUpdate.setText(sdf.format(transaction.getLastEventDate()));

            tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            tcAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount().doubleValue()).asObject());
            tcQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
            tcWeight.setCellValueFactory(cellData -> {
                if (cellData.getValue().getWeight() != null)
                    return new SimpleLongProperty(cellData.getValue().getWeight()).asObject();
                return null;
            });
            tcShippingCost.setCellValueFactory(cellData -> {
                if (cellData.getValue().getWeight() != null)
                    return new SimpleDoubleProperty(cellData.getValue().getWeight()).asObject();
                return null;
            });


            observableList = FXCollections.observableList(transaction.getItems());
            tvProducts.setItems(observableList);

        }



    }

}
