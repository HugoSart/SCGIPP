package scgipp.ui.visible;

import br.com.uol.pagseguro.domain.TransactionSummary;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * User: hugo_<br/>
 * Date: 22/11/2017<br/>
 * Time: 18:59<br/>
 */
public class ObservableTransactionSummary {

    private TransactionSummary transactionSummary;

    public ObservableTransactionSummary(TransactionSummary transactionSummary) {
        this.transactionSummary = transactionSummary;
    }

    public StringProperty codeProperty() { return new SimpleStringProperty(transactionSummary.getCode()); }

    public StringProperty dateProperty() { return new SimpleStringProperty(transactionSummary.getDate().toString()); }

    public StringProperty typeProperty() { return new SimpleStringProperty(transactionSummary.getType().toString()); }

    public FloatProperty priceProperty() { return new SimpleFloatProperty(transactionSummary.getGrossAmount().floatValue()); }

    public StringProperty statusProperty() { return new SimpleStringProperty(transactionSummary.getStatus().toString()); }

    public StringProperty paymentProperty() { return new SimpleStringProperty(transactionSummary.getPaymentMethod().getType().toString()); }

    public TransactionSummary getTransactionSummary() { return transactionSummary; }

}
