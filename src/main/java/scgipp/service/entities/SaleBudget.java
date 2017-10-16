package scgipp.service.entities;

import scgipp.data.hibernate.Entity;
import scgipp.service.entities.embbeded.EmbeddableAddress;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class SaleBudget extends Entity {

    @OneToMany
    private List<Product> products;

    private EmbeddableAddress address;

    public SaleBudget() {}

    public SaleBudget(List<Product> products, EmbeddableAddress address) {
        this.products = products;
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

}
