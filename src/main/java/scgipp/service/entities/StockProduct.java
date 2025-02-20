package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

@Entity
public class StockProduct extends BaseEntity<Integer> {

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    protected StockProduct(){}

    /*public StockProduct(String name, String description, Integer quantity, Double price){
        this();
        this.product.setName(name);
        this.product.setDescription(description);
        this.quantity = quantity;
        this.price = price;
    }*/

    public StockProduct(Product product, Integer quantity, Double price){
        this();
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setPrice(price);
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

    public void setPrice(Double price){
        this.price = price;
    }

    public Double getPrice(){
        return price;
    }

}