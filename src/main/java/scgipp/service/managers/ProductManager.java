package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.system.log.Log;

public class ProductManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addProduct(Product product){
        Integer id = dbManager.add(product);
        if(product.getId() != null){
            Log.show("DATABASE", "Product", "Product <id = " + product.getId() +
                    "> has been added to the database.");
        }
        return id;
    }

    public static void removeProduct(Product product){
        dbManager.remove(product);
        if(product.getId() != null){
            Log.show("DATABASE", "Product", "An error has occurred.\nProduct <id = "
                    + product.getId() + "has not been removed");
        }
        else {
            Log.show("DATABASE", "Product", "The product has been removed.");
        }
    }

    public static void updateProduct(Product product){
        dbManager.update(product);
        Log.show("DATABASE", "Product", "The stock product has been updated.");
    }

}


