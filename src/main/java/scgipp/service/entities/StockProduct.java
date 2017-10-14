package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class StockProduct extends Entity{

    @OneToOne
    private Product product = new Product();

    @Column(nullable = false)
    private Float price;

    private Integer quantity;

    protected StockProduct(){};

    public StockProduct(String name, String description, Integer quantity, Float price){
        this();
        this.product.setName(name);
        this.product.setDescription(description);
        this.quantity = quantity;
        this.price = price;
    }

    public StockProduct(Product product, Integer quantity, Float price){
        this();
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public void setProduct(Product product){
        this.product.setName(product.getName());
        this.product.setDescription(product.getDescription());
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

    public void setPrice(Float price){
        this.price = price;
    }

    public Float getPrice(){
        return price;
    }
//a
}
