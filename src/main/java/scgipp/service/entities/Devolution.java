package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Devolution extends BaseEntity {

    @OneToOne
    private Sale sale;

    public Devolution(){}

    public Devolution(Sale sale) {
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
