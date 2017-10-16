package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

@javax.persistence.Entity
public class Sale extends Entity {

    @Column
    private LocalDate date;

    @OneToOne
    private Devolution devolution;

    @OneToOne
    private SaleBudget saleBudget;

    @ManyToOne
    private User user;

    protected Sale(){}

    public Sale(LocalDate date, Devolution devolution, SaleBudget saleBudget, User user, Customer customer) {
        this.date = date;
        this.devolution = devolution;
        this.saleBudget = saleBudget;
        //this.user = user;
        //this.client = client;
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

}
