package scgipp.ui.visible;

import javafx.beans.property.*;
import scgipp.service.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ObservableProduct {

    private Product product;

    public ObservableProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(product.getId());
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(product.getName());
    }

    public IntegerProperty quantityProperty() { return new SimpleIntegerProperty(product.getQuantity()); }

    public DoubleProperty priceProperty() { return new SimpleDoubleProperty(product.getAmount().doubleValue()); }

    public StringProperty descriptionProperty() { return new SimpleStringProperty(product.getDescription());}

    public LongProperty weightProperty() { return new SimpleLongProperty(product.getWeight());}

    public static List<ObservableProduct> productListTAsObservableProductList(List<Product> list) {

        List<ObservableProduct> observableProducts = new ArrayList<>();
        for (Product product : list)
            observableProducts.add(new ObservableProduct(product));
        return observableProducts;

    }

    public DoubleProperty totalPriceProperty() { return new SimpleDoubleProperty(product.getAmount().doubleValue() * this.getProduct().getQuantity()); }

}
