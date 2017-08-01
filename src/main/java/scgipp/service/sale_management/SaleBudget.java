package scgipp.service.sale_management;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.service.Address;
import scgipp.service.product_management.Product;
import scgipp.service.transportadora_management.Transportadora;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
public class SaleBudget {

    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    @OneToOne
    public Transportadora trasporter;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Product> products;

    public String zip = null;

    public double multiplier = 1;

    public double totalPrice() {

        double total = 0;

        for (Product p : products) {
            total += p.getCustomerPrice();
        }

        total += freight();

        return total;

    }

    public double priceWithDiscount() {
        return totalPrice() * multiplier;
    }

    public double freight() {
        Random r = new Random();
        return 5 + (10 - 5) * r.nextDouble();
    }

    public Integer getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty productsProperty() {

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(Product s : products) {
            builder.append(s.getName());
            if (i < (products.size() - 1)) builder.append(", ");
            i++;
        }
        return new SimpleStringProperty(builder.toString());

    }

}
