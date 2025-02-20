package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Transaction;
import org.hibernate.annotations.SQLDelete;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sale extends BaseEntity<Integer> {

    @ManyToOne
    private User user;

    @ManyToOne
    private Customer customer;

    @Column
    private LocalDate date;

    @Column
    private java.math.BigDecimal totalPrice;

    @OneToMany(fetch = FetchType.LAZY)
    public List<SaleProduct> productsList;

    private String transactionCode;

    public Sale(){}

    public Sale(User user, Customer customer, String transactionCode, List<SaleProduct> saleBudget) {
        this.user = user;
        this.customer = customer;
        this.transactionCode = transactionCode;
        this.date = LocalDate.now();
        this.productsList = new ArrayList<SaleProduct>(saleBudget);

        /*
        for (Product p : saleBudget) {
            this.totalPrice = totalPrice.add(p.getAmount());
        }
        */
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal t)
    {
        this.totalPrice = t;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
