package scgipp.service.sale_management;

import scgipp.service.Address;
import scgipp.service.product_management.Product;
import scgipp.service.transportadora_management.Transportadora;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaleBudget {

    public Transportadora trasporter;

    public List<Product> products = new ArrayList<>();

    public Address address = null;
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

}
