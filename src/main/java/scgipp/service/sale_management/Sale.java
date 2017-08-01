package scgipp.service.sale_management;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.service.customer_management.Customer;
import scgipp.service.product_management.Product;
import scgipp.service.transportadora_management.Transportadora;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {

    public static final int PENDING  = -1;
    public static final int CANCELED = 0;
    public static final int PAID     = 1;

    @Id
    @GeneratedValue
    public Integer id;

    @OneToOne
    public Customer customer;

    @OneToOne
    public SaleBudget saleBudget;

    @Column
    public Date date;

    @Column
    public int status;

    public Sale() {

    }

    public Sale(Customer customer, SaleBudget saleBudget) {
        this.customer = customer;
        this.date = Calendar.getInstance().getTime();
        this.saleBudget = saleBudget;
        this.status = PENDING;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty customerProperty() {
        return new SimpleStringProperty(customer.getName());
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

    public SimpleIntegerProperty statusProperty() {
        return new SimpleIntegerProperty(status);
    }

}
