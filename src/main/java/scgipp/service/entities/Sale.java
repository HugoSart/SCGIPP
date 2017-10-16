package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@javax.persistence.Entity
public class Sale extends Entity {

    @Column
    private Date date;

    @OneToOne
    private Devolution devolution;

    @OneToOne
    private EstimativeSale estimativeSale;

    /*
    @ManyToOne
    private User user;

    @ManyToOne
    private Client client;
    */
    protected Sale(){};

    public Sale(Date date, Devolution devolution, EstimativeSale estimativeSale, User user, Customer customer) {
        this.date = date;
        this.devolution = devolution;
        this.estimativeSale = estimativeSale;
        //this.user = user;
        //this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Devolution getDevolution() {
        return devolution;
    }

    public void setDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    public EstimativeSale getEstimativeSale() {
        return estimativeSale;
    }

    public void setEstimativeSale(EstimativeSale estimativeSale) {
        this.estimativeSale = estimativeSale;
    }

    /*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    */

}
