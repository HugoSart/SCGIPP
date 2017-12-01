package scgipp.ui.visible;

import javafx.beans.property.*;
import scgipp.service.entities.CartItem;
import scgipp.service.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ObservableCartItem {


    private CartItem cartItem;

    public ObservableCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(cartItem.getId());
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(cartItem.getProduct().getName());
    }

    public IntegerProperty quantityProperty() { return new SimpleIntegerProperty(cartItem.getQuantity()); }

    public DoubleProperty priceProperty() { return new SimpleDoubleProperty(cartItem.getPrice()); }

    public static List<ObservableCartItem> cartItemListTAsObservableCartItemList(List<CartItem> list) {

        List<ObservableCartItem> observableCartItems = new ArrayList<>();
        for (CartItem cartItem : list)
            observableCartItems.add(new ObservableCartItem(cartItem));
        return observableCartItems;
    }

    @Override
    public String toString() {
        return getCartItem().getQuantity() + " " + getCartItem().getProduct().getName();
    }
}
