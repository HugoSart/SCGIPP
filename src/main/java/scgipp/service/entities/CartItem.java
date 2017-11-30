package scgipp.service.entities;

import javafx.beans.property.IntegerProperty;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

public class CartItem extends BaseEntity<Integer> {

    @ManyToMany
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    protected CartItem(){}

    public CartItem(Product product, Integer quantity, Double price){
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
