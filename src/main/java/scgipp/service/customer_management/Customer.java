package scgipp.service.customer_management;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Customer {
    @Column(unique = true, nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private String cep;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable =  false)
    private String telephone;
    @Column(nullable = false)
    private final Adress adress = new Adress();

    private Customer(){}

    public abstract Customer createCustomer();
    public abstract boolean deleteCustomer();

    public void setAdress(String street, String number, String neighborhood){
        adress.setStreet(street);
        adress.setNumber(number);
        adress.setNeighborhood(neighborhood);
    }

    public void setAdress(String street, String number, String neighborhood, String tip){
        adress.setStreet(street);
        adress.setNumber(number);
        adress.setNeighborhood(neighborhood);
        adress.setTip(tip);
    }

    public Adress getAdress(){
        return adress;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public String getCep(){
        return cep;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return telephone;
    }
}

class Adress {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String neighborhood;
    private String tip;

    Adress(){}

    Adress(String street, String number, String neighborhood){
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
    }

    Adress(String street, String number, String neighborhood, String tip){
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.tip = tip;
    }

    void setStreet(String street){
        this.street = street;
    }

    String getStreet(){
        return street;
    }

    void setNumber(String number){
        this.number = number;
    }

    String getNumber(){
        return number;
    }

    void setNeighborhood(String neighborhood){
        this.neighborhood = neighborhood;
    }

    String getNeighborhood(){
        return neighborhood;
    }

    void setTip(String tip){
        this.tip = tip;
    }

    String getTip(){
        return tip;
    }
}