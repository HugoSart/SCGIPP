package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.system.log.Log;

import java.util.List;

public class ProductManager {

    private static ProductManager instance = null;

    private static DBManager dbManager = DBConnection.manager();

    public static ProductManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new ProductManager();
        return instance;
    }

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
        if(product.getId() != null)
            Log.show("DATABASE", "Product", "The stock product has been updated.");
    }

    public static Product getProduct(Product product){
        return dbManager.get(Product.class, product.getId());
    }

    public List<Product> listAll(){
        return dbManager.list(Product.class);
    }

}

