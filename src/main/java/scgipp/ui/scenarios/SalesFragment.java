package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.TransactionSearchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.Main;
import scgipp.service.entities.Sale;
import scgipp.service.entities.SaleProduct;
import scgipp.service.entities.User;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.SaleManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableSale;
import scgipp.ui.visible.ObservableTransactionSummary;
import scgipp.ui.visible.ObservableUser;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 06/11/2017<br/>
 * Time: 22:08<br/>
 */
public class SalesFragment extends Fragment {

    private SaleManager saleManager = SaleManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance().getInstance();


    @FXML private AnchorPane customTableView;
    @FXML private TextField tfSearch;
    @FXML private TableView<ObservableTransactionSummary> tvTransactions;
    @FXML private TableColumn<ObservableTransactionSummary, String> tcCode;
    @FXML private TableColumn<ObservableTransactionSummary, String> tcDate;
    @FXML private TableColumn<ObservableTransactionSummary, String> tcType;
    @FXML private TableColumn<ObservableTransactionSummary, Number> tcPrice;
    @FXML private TableColumn<ObservableTransactionSummary, String> tcPaymentMethod;
    @FXML private TableColumn<ObservableTransactionSummary, String> tcStatus;
    @FXML private DatePicker dpInitial;
    @FXML private DatePicker dpFinal;
    @FXML private Button btSearch;
    @FXML private Button btNew;
    @FXML private Button btStatus;
    @FXML private ProgressIndicator piProgress;

    @FXML private ObservableList<ObservableSale> saleObservableList;

    @FXML
    private TableView<ObservableSale> tvSales;

    @FXML
    private TableColumn<ObservableSale, String> tcCustomer;

    @FXML
    private TableColumn<ObservableSale, String> tcUser;

    @FXML
    private TableColumn<ObservableSale, String> tcSaleDate;

    @FXML
    private TableColumn<ObservableSale, Double> tcTotalSalePrice;

    @FXML
    private TableColumn<ObservableSale, Integer> tcSaleId;

    @FXML
    private Button btNewSale;

    @FXML
    private Button btRemoveSale;

    private ObservableList<ObservableTransactionSummary> observableTransactionSummaries;

    public SalesFragment() {
        super("fxml/fragment_sales.fxml");
}

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setUpPagSeguroTab();
            setUpLocalTab();
    }

    private void setUpPagSeguroTab() {


        btSearch.setOnAction(event -> searchPagSeguro());
        tcCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        tcDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tcType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        tcPaymentMethod.setCellValueFactory(cellData -> cellData.getValue().paymentProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tvTransactions.setRowFactory( tv -> {
            TableRow<ObservableTransactionSummary> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ObservableTransactionSummary rowData = row.getItem();
                    SaleInfoScenario saleInfoScenario = new SaleInfoScenario();
                    saleInfoScenario.putExtra("summary", rowData.getTransactionSummary());
                    Spawner.startScenario(saleInfoScenario, this);
                }
            });
            return row ;
        });

        btNew.setOnAction(event -> {
            Scenario newPagSeguroSaleScenario = new NewPagSeguroSaleScenario();
            Spawner.startScenario(newPagSeguroSaleScenario, this);
        });

    }

    private void setUpLocalTab() {
        List<Sale> saleList = saleManager.getAll();
        saleObservableList = FXCollections.observableList(ObservableSale.saleListTAsObservableSaleList(saleList));
        tcSaleId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcSaleDate.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        tcCustomer.setCellValueFactory(cellData -> cellData.getValue().getCustomerNameProperty());
        tcTotalSalePrice.setCellValueFactory(cellData -> cellData.getValue().getTotalAmout().asObject());
        tcUser.setCellValueFactory(cellData -> cellData.getValue().getUserProperty());
        tvSales.setItems(saleObservableList);

        btNewSale.setOnAction(event -> {
            FeedbackScenario addSaleScenario = new AddSaleScenario();
            Spawner.startFeedbackScenario(addSaleScenario, 0, this, (requestCode, resultCode, data) -> {
                Sale sale = (Sale)data.get(AddSaleScenario.FEEDBACK_NEW_SALE);
                saleManager.addSale(sale);
                saleObservableList.add(new ObservableSale(sale));
                tvSales.refresh();
            });

        });

        btRemoveSale.setOnAction(event -> {
            ObservableSale observableSale = tvSales.getSelectionModel().getSelectedItem();
            Integer sale_qtd;
            Integer current_qtd;
            for(SaleProduct sp : observableSale.getSale().productsList)
            {
                sale_qtd = sp.getQuantity();
                current_qtd = sp.getProduct().getQuantity();
                sp.getProduct().setQuantity(sale_qtd + current_qtd);
                productManager.updateProduct(sp.getProduct());
            }
            saleManager.removeSale(observableSale.getSale());
            saleObservableList.remove(observableSale);
            tvSales.refresh();
        });

        /*
        FilteredList<ObservableSale> filteredData = new FilteredList<>(saleObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getSale().getUser().getLogin()).toLowerCase().contains(lowerCaseFilter)) return true;
            //else if (String.valueOf(myObject.getSale().getCustomer()).toLowerCase().contains(lowerCaseFilter)) return true;
            //else if (String.valueOf(myObject.getSale().getDate()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvSales.setItems(filteredData);
        */

    }

    private void searchPagSeguro() {

        new Thread(new LoadTransactionSummariesTask()).start();

    }

    protected class LoadTransactionSummariesTask extends Task<List<ObservableTransactionSummary>> {

        @Override
        protected List<ObservableTransactionSummary> call() throws Exception {

            btSearch.setVisible(false);
            piProgress.setVisible(true);

            TransactionSearchResult result = null;

            try {
                Date initialDate = Date.from(dpInitial.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date finalDate = Date.from(dpFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                result = TransactionSearchService.searchByDate(Main.getCredentials(), initialDate, finalDate, 1, 20);
            } catch (PagSeguroServiceException e) {
                System.err.println("Could not search PagSeguro transactions: " + e.getMessage());
            }

            List<TransactionSummary> summaries = result.getTransactionSummaries();
            List<ObservableTransactionSummary> observableTransactionSummaries = new ArrayList<>();
            for (TransactionSummary summary : summaries)
                observableTransactionSummaries.add(new ObservableTransactionSummary(summary));

            return observableTransactionSummaries;
        }

        @Override
        protected void succeeded() {
            observableTransactionSummaries = FXCollections.observableArrayList(getValue());
            FilteredList<ObservableTransactionSummary> filteredData = new FilteredList<>(observableTransactionSummaries, p -> true);
            tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getTransactionSummary().getCode()).toLowerCase().contains(lowerCaseFilter)) return true;
                else if (String.valueOf(myObject.getTransactionSummary().getDate()).toLowerCase().contains(lowerCaseFilter)) return true;
                else if (String.valueOf(myObject.getTransactionSummary().getStatus()).toLowerCase().contains(lowerCaseFilter)) return true;
                else if (String.valueOf(myObject.getTransactionSummary().getStatus()).toLowerCase().contains(lowerCaseFilter)) return true;
                return false;
            }));
            tvTransactions.setItems(filteredData);
            btSearch.setVisible(true);
            piProgress.setVisible(false);
        }

        @Override
        protected void failed() {
            btSearch.setVisible(true);
            piProgress.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na busca");
            alert.setContentText("O intervalo de datas precisa ser igual ou menor a 30 dias.");

            alert.showAndWait();

        }

    }

}
