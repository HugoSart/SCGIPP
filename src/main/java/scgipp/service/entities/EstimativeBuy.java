package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class EstimativeBuy extends Entity {

    @ManyToMany(mappedBy = "EstimativeSales")
    private List<Product> productList = new ArrayList<>();

    @OneToOne
    private Supplier supplier;

    @ManyToOne
    private Buy buy;

    //private ShippingCompany shippingCompany;

    protected EstimativeBuy(){};

    public EstimativeBuy(List<Product> productList, Sale sale, Supplier supplier, Buy buy) {
        this.productList = productList;
        this.supplier = supplier;
        this.buy = buy;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

}
