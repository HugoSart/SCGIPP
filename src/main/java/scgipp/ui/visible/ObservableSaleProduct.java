package scgipp.ui.visible;

import javafx.beans.property.*;
import scgipp.service.entities.Product;
import scgipp.service.entities.SaleProduct;

import java.util.ArrayList;
import java.util.List;

public class ObservableSaleProduct {

    private SaleProduct saleProduct;

    public ObservableSaleProduct(SaleProduct saleProduct) {
        this.saleProduct = saleProduct;
    }

    public SaleProduct getSaleProduct() {
        return saleProduct;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(saleProduct.getProduct().getId());
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(saleProduct.getProduct().getName());
    }

    public IntegerProperty quantityProperty() { return new SimpleIntegerProperty(saleProduct.getQuantity()); }

    public DoubleProperty priceProperty() { return new SimpleDoubleProperty(saleProduct.getProduct().getAmount().doubleValue()); }

    public StringProperty descriptionProperty() { return new SimpleStringProperty(saleProduct.getProduct().getDescription());}

    public LongProperty weightProperty() { return new SimpleLongProperty(saleProduct.getProduct().getWeight());}

    public static List<ObservableSaleProduct> productListTAsObservableSaleProductList(List<SaleProduct> list) {

        List<ObservableSaleProduct> observableSaleProducts = new ArrayList<>();
        for (SaleProduct sproduct : list)
            observableSaleProducts.add(new ObservableSaleProduct(sproduct));
        return observableSaleProducts;

    }

    public DoubleProperty totalPriceProperty() { return new SimpleDoubleProperty(saleProduct.getProduct().getAmount().doubleValue() * this.saleProduct.getQuantity()); }

}
