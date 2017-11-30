package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.CartItem;
import scgipp.service.entities.Product;
import scgipp.system.log.Log;

import java.util.List;

public class CartItemManager {

    private static CartItemManager instance = null;

    private static DBManager dbManager = DBConnection.manager();

    public static CartItemManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new CartItemManager();
        return instance;
    }

    public static Integer addCartItem(CartItem cartItem){
        Integer id = dbManager.add(cartItem);
        if(cartItem.getId() != null){
            Log.show("DATABASE", "CartItem", "CartItem <id = " + cartItem.getId() +
                    "> has been added to the database.");
        }
        return id;
    }

    public static void removeCartItem(CartItem cartItem){
        dbManager.remove(cartItem);
        if(cartItem.getId() != null){
            Log.show("DATABASE", "CartItem", "An error has occurred.\nCartItem <id = "
                    + cartItem.getId() + "has not been removed");
        }
        else {
            Log.show("DATABASE", "CartItem", "The cart item has been removed.");
        }
    }

    public static void updateCartItem(CartItem cartItem){
        dbManager.update(cartItem);
        if(cartItem.getId() != null)
            Log.show("DATABASE", "CartItem", "The cart item has been updated.");
    }

    public static CartItem getCartItem(CartItem cartItem){
        return dbManager.get(CartItem.class, cartItem.getId());
    }

    public List<CartItem> listAll(){
        return dbManager.list(CartItem.class);
    }
}
