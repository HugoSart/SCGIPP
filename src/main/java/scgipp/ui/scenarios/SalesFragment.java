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
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableTransactionSummary;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 06/11/2017<br/>
 * Time: 22:08<br/>
 */
public class SalesFragment extends Fragment {

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
    @FXML private ProgressIndicator piProgress;

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

    }

    private void setUpLocalTab() {

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
