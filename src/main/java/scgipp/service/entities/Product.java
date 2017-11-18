<<<<<<< HEAD
package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;

@javax.persistence.Entity
public class Product extends BaseEntity {

    @Column (nullable = false, unique = true)
    private String name;

    private String description;

    protected Product() {}

    public Product(String name){
        this();
        setName(name);
    }

    public Product(String name, String description){
        this(name);
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
=======
package scgipp.service.entities;

import scgipp.data.hibernate.BaseEntity;

import javax.persistence.Column;

@javax.persistence.Entity
public class Product extends BaseEntity {

    @Column (nullable = false, unique = true)
    private String name;

    private String description;

    protected Product() {}

    public Product(String name){
        this();
        setName(name);
    }

    public Product(String name, String description){
        this(name);
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
>>>>>>> [C]ObservableCustomer
