package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Transaction;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
public class Sale extends BaseEntity<Integer> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Customer customer;

    private String transactionCode;

    public Sale(){}

    public Sale(User user, Customer customer, String transactionCode) {
        this.user = user;
        this.customer = customer;
        this.transactionCode = transactionCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
