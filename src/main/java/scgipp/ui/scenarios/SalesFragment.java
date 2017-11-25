package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.PaymentMethod;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.com.uol.pagseguro.enums.TransactionType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.service.TransactionSearchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.Main;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.visible.ObservableTransactionSummary;

import java.math.BigDecimal;
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

        observableTransactionSummaries = FXCollections.observableArrayList();

        btSearch.setOnAction(event -> searchPagSeguro());
        tcCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        tcDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tcType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        tcPaymentMethod.setCellValueFactory(cellData -> cellData.getValue().paymentProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

    }

    private void setUpLocalTab() {

    }

    private void searchPagSeguro() {

        new Thread(new LoadTransactionsTask()).start();

    }

    protected class LoadTransactionsTask extends Task<List<ObservableTransactionSummary>> {

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
            tvTransactions.getItems().setAll(getValue());
            btSearch.setVisible(true);
            piProgress.setVisible(false);
        }

    }

}
