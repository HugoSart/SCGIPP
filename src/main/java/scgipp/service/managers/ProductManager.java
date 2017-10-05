package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.system.log.Log;

public class ProductManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addUser(Product product){
        Integer id = dbManager.add(product);
        if(product.getId() != null){
            Log.show("DATABASE", "Product", "Produto <id = " + product.getId() +
                    "> foi adicionado ao banco de dados");
        }
        return id;
    }
}
