package scgipp.service.entities;

import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class Product extends Entity {


    @ManyToMany
    private List<EstimativeSale> estimativeSaleList = new ArrayList<>();

    @ManyToMany
    private List<EstimativeBuy> estimativeBuyList = new ArrayList<>();

    @Column (nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @OneToOne
    private Sale sale;

    protected Product(){}

    public Product(String name){
        this();
        setName(name);
    }

    public Product(String name, String description){
        this();
        setName(name);
        setDescription(description);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
