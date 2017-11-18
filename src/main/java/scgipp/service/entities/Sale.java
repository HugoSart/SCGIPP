<<<<<<< HEAD
package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@javax.persistence.Entity
public class Sale extends BaseEntity {

    @Column
    private LocalDate date;

    @OneToOne
    private Devolution devolution;

    @OneToOne
    private SaleBudget saleBudget;

    @ManyToOne
    private User user;

    public Sale(){}

    public Sale(LocalDate date, Devolution devolution, SaleBudget saleBudget, User user) {
        this.date = date;
        this.devolution = devolution;
        this.saleBudget = saleBudget;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Devolution getDevolution() {
        return devolution;
    }

    public void setDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    public SaleBudget getSaleBudget() {
        return saleBudget;
    }

    public void setSaleBudget(SaleBudget saleBudget) {
        this.saleBudget = saleBudget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
=======
package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@javax.persistence.Entity
public class Sale extends BaseEntity {

    @Column
    private LocalDate date;

    @OneToOne
    private Devolution devolution;

    @OneToOne
    private SaleBudget saleBudget;

    @ManyToOne
    private User user;

    public Sale(){}

    public Sale(LocalDate date, Devolution devolution, SaleBudget saleBudget, User user) {
        this.date = date;
        this.devolution = devolution;
        this.saleBudget = saleBudget;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Devolution getDevolution() {
        return devolution;
    }

    public void setDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    public SaleBudget getSaleBudget() {
        return saleBudget;
    }

    public void setSaleBudget(SaleBudget saleBudget) {
        this.saleBudget = saleBudget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
>>>>>>> [C]ObservableCustomer
