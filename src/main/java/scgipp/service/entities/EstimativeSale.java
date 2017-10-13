package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class EstimativeSale extends Entity {

    @OneToOne
    private Sale sale;

    @OneToOne
    private ShippingCompany shippingCompany;

    @ManyToMany(mappedBy = "EstivativesBuySale")
    private List<Product> productList = new ArrayList<>();

    protected EstimativeSale(){};

    public EstimativeSale(Sale sale, ShippingCompany shippingCompany) {
        this.sale = sale;
        this.shippingCompany = shippingCompany;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

}
