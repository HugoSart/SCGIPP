package scgipp.service.entities;

import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SaleProduct extends BaseEntity<Integer> {

    @Column
    private Integer quantity;

    @ManyToOne
    private Product product;

    public SaleProduct(){}

    public SaleProduct(Integer quantidade, Product produto)
    {
        this.quantity = quantidade;
        this.product = produto;
    }

    public Product getProduct()
    {
        return this.product;
    }

    public Integer getQuantity()
    {
        return this.quantity;
    }
}
