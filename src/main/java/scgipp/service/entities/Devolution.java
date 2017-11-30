package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Devolution extends BaseEntity<Integer> {

    @ManyToOne
    private Sale sale;

    @ManyToOne
    private Product product;

    Date devolutionDate;
    Boolean restoreToStock;

    public Devolution(){}

    public Devolution(Sale sale, Product product, Date devolutionDate, Boolean restoreToStock) {
        this.devolutionDate = devolutionDate;
        this.product = product;
        this.sale = sale;
        this.restoreToStock = restoreToStock;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public Boolean getRestoreToStock() {
        return restoreToStock;
    }

    public void setRestoreToStock(Boolean restoreToStock) {
        this.restoreToStock = restoreToStock;
    }
}

