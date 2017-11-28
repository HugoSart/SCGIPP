package scgipp.ui.visible;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.service.entities.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by kira
 */
public class ObservableCustomer {

    private Customer customer;

    public ObservableCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(customer.getId());
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(customer.getName());
    }

    public static List<ObservableCustomer> custumerListTAsObservableUserList(List<Customer> list) {

        List<ObservableCustomer> observableCustomer = new ArrayList<>();
        for (Customer m : list)
            observableCustomer.add(new ObservableCustomer(m));
        return observableCustomer;

    }

    public StringProperty cpfProperty()
    {
        return new SimpleStringProperty(customer.getCpf_cnpj());
    }

    public StringProperty addressProperty() {
        return new SimpleStringProperty(customer.getAddresses().get(0).getStreet().toString());
    }
    public StringProperty phoneProperty() {
        return new SimpleStringProperty(customer.getPhones().get(0).getNumber().toString());
    }
}
