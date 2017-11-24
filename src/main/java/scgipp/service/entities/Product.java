package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Item;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;
import java.math.BigDecimal;

@javax.persistence.Entity
public class Product extends Item {

    protected Product() {}

    public Product(String description, Integer quantity, BigDecimal amount){
        super(description, quantity, amount);
    }

}
