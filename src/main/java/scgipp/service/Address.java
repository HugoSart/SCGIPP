package scgipp.service;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsart on 13/05/17.
 */
@Embeddable
public class Address {

    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private String comp;
    private String zip;

    public Address() {}

    public Address(String country, String state, String city, String street, String number, String comp, String zip) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.comp = comp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

}
