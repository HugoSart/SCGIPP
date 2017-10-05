package scgipp.service.entities;

import scgipp.data.hibernate.Entity;


public class Product extends Entity {

    private String name;
    private int totalQuantity;
    private float price;
    private String description;

    protected Product(){}

    public Product(String name, int quantity, float price){

        this();
        setName(name);
        setTotalQuantity(quantity);
        setPrice(price);
    }

    public Product(String name, int quantity, float price, String description){
        this();
        setName(name);
        setTotalQuantity(quantity);
        setPrice(price);
        setDescription(description);
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setTotalQuantity(int quantity){
        this.totalQuantity = quantity;
    }

    public int getTotalQuantity(){
        return totalQuantity;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public float getPrice(){
        return price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        String productInformation = "Nome = " + name + "\nEstoque total = " + totalQuantity +
                                     "\nPreço = " + price + "\nDescrição = " + description;
        return productInformation;
    }
}
