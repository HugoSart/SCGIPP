package scgipp.ui.visible;

import javafx.beans.property.*;
import scgipp.service.entities.Sale;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Kira
 * Date: 29/11/2017<br/>
 * Time: 20:54<br/>
 */
public class ObservableSale {

    private Sale sale;

    public ObservableSale(Sale sale) {
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(sale.getId());
    }

    public StringProperty getCustomerNameProperty() {
        return new SimpleStringProperty(sale.getCustomer().getName());
    }

    public StringProperty getUserProperty() {
        return new SimpleStringProperty(sale.getUser().getLogin());
    }

    public StringProperty getDateProperty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = sale.getDate().format(formatter);
        return new SimpleStringProperty(formattedString);
    }

    public SimpleDoubleProperty getTotalAmout(){
        return new SimpleDoubleProperty(this.sale.getTotalPrice().doubleValue());
    }


    public static List<ObservableSale> saleListTAsObservableSaleList(List<Sale> list) {

        List<ObservableSale> observableSale = new ArrayList<>();
        for (Sale sale : list)
            observableSale.add(new ObservableSale(sale));
        return observableSale;

    }

}
