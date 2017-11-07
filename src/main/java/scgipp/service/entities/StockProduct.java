package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StockProduct extends BaseEntity {

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private double purchasePrice;

    @Column(nullable = false)
    private double salePrice;

    @Column(nullable = false)
    private Integer quantity;

    protected StockProduct() {}

    public StockProduct(Product product, Integer quantity, double salePrice){
        this();
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setSalePrice(salePrice);
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double buyPrice) {
        this.purchasePrice = buyPrice;
    }

    public void setSalePrice(double price){
        this.salePrice = price;
    }

    public double getSalePrice(){
        return salePrice;
    }

}