package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.OneToOne;

/**
* Created by: Dario
* On: 4/10/17
**/

@javax.persistence.Entity
public class Supplier extends Entity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column
    private String anddress;

    @Column
    private int phoneNumber;

    @OneToOne
    private EstimativeSale estimativeSale;

    protected Supplier(){}

    public Supplier(String name, String cnpj, String anddress, int phoneNumber, EstimativeSale estimativeSale) {
        this.name = name;
        this.cnpj = cnpj;
        this.anddress = anddress;
        this.phoneNumber = phoneNumber;
        this.estimativeSale = estimativeSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAnddress() {
        return anddress;
    }

    public void setAnddress(String anddress) {
        this.anddress = anddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EstimativeSale getEstimativeSale() {
        return estimativeSale;
    }

    public void setEstimativeSale(EstimativeSale estimativeSale) {
        this.estimativeSale = estimativeSale;
    }

}

