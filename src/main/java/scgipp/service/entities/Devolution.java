package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Devolution extends BaseEntity<Integer> {

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
