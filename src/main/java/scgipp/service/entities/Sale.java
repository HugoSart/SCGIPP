package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;

@javax.persistence.Entity
public class Sale extends Entity {

    @ManyToOne
    private User user;

    @OneToMany
    private EstimativeSale estimativeSale;

    @OneToOne
    private Devolution devolution;

    @Column
    private Date date;

    protected Sale(){};

    public Sale(User user, EstimativeSale estimativeSale, Date date) {
        this.user = user;
        this.estimativeSale = estimativeSale;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EstimativeSale getEstimativeSale() {
        return estimativeSale;
    }

    public void setEstimativeSale(EstimativeSale estimativeSale) {
        this.estimativeSale = estimativeSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
