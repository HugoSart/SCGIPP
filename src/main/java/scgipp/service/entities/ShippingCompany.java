package scgipp.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShippingCompany extends scgipp.data.hibernate.Entity {

    @Column
    private String name;

    @OneToOne
    private EstimativeSale estimativeSale;

    @OneToMany
    private List<EstimativeBuy> estimativeBuyList = new ArrayList<>();

    protected ShippingCompany(){}

    public ShippingCompany(String name, EstimativeSale estimativeSale) {
        this.name = name;
        this.estimativeSale = estimativeSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EstimativeSale getEstimativeSale() {
        return estimativeSale;
    }

    public void setEstimativeSale(EstimativeSale estimativeSale) {
        this.estimativeSale = estimativeSale;
    }

    public void addEstimativeBuy(EstimativeBuy estimativeBuy){
        estimativeBuyList.add(estimativeBuy);
    }

}
