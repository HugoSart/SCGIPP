package scgipp.service.entities;

import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Devolution extends scgipp.data.hibernate.Entity {

    @OneToOne
    private Sale sale;

    protected Devolution(){}

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
