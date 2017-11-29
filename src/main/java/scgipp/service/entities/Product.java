package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Item;
import org.hibernate.annotations.SQLDelete;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity<Integer> {

    private Item item;

    private String name;

    protected Product() {}

    public Product(String name, String description, Integer quantity, BigDecimal amount, Long weight){
        item = new Item();
        setDescription(description);
        setQuantity(quantity);
        setAmount(amount);
        setWeight(weight);
        this.name = name;
    }

    public Item getItem(){return this.item;}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return item.getDescription();
    }

    /**
     * @param description
     *            the product description to set
     */
    public void setDescription(String description) {
        item.setDescription(description);
    }

    /**
     * @return the product quantity
     */
    public Integer getQuantity() {
        return item.getQuantity();
    }

    /**
     * @param quantity
     *            the product quantity to set
     */
    public void setQuantity(Integer quantity) {
        item.setQuantity(quantity);
    }

    /**
     * @return the product unit price
     */
    public BigDecimal getAmount() {
        return item.getAmount();
    }

    /**
     * @param amount
     *            the product unit price to set
     */
    public void setAmount(BigDecimal amount) {
        item.setAmount(amount);
    }

    /**
     * @return the product unit weight, in grams
     */
    public Long getWeight() {
        return item.getWeight();
    }

    /**
     * @param weight
     *            the product unit weight, in grams, to set
     */
    public void setWeight(Long weight) {
        item.setWeight(weight);
    }

    /**
     * @return the product unit shipping cost
     */
    public BigDecimal getShippingCost() {
        return item.getShippingCost();
    }

    /**
     * @param shippingCost
     *            the product unit shipping cost to set
     */
    public void setShippingCost(BigDecimal shippingCost) {
        item.setShippingCost(shippingCost);
    }


}
