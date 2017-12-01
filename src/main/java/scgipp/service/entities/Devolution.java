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

    String devolutionDate;
    Boolean restoreToStock;
    Integer quantity;

    public Devolution(){}

    public Devolution(Sale sale, Product product, String devolutionDate, Boolean restoreToStock, Integer quantity) {
        this.devolutionDate = devolutionDate;
        this.product = product;
        this.sale = sale;
        this.restoreToStock = restoreToStock;
        this.quantity = quantity;
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

    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public Boolean getRestoreToStock() {
        return restoreToStock;
    }

    public void setRestoreToStock(Boolean restoreToStock) {
        this.restoreToStock = restoreToStock;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

