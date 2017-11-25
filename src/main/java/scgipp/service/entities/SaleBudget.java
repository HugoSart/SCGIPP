package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Address;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SaleBudget extends BaseEntity<Integer> {

    @OneToMany
    private List<Product> products;

    private Address address;

    public SaleBudget() {}

    public SaleBudget(List<Product> products, Address address) {
        this.products = products;
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }


}
