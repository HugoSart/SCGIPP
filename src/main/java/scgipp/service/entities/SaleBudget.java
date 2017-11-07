package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Transaction;
import scgipp.data.hibernate.BaseEntity;
import scgipp.service.entities.embbeded.EmbeddableAddress;

import javax.persistence.*;
import java.util.List;

@Entity
public class SaleBudget extends BaseEntity {

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
