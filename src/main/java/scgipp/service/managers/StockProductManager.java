package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.StockProduct;
import scgipp.system.log.Log;

import java.util.List;

public class StockProductManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addStockProduct(StockProduct stockProduct){
        Integer id = dbManager.add(stockProduct);
        if(stockProduct.getId() != null){
            Log.show("DATABASE", "StockProduct", "StockProduct <id = " + stockProduct.getId() +
                    "> has been added to the database.");
        }
        return id;
    }

    public static void removeStockProduct(StockProduct stockProduct){
        dbManager.remove(stockProduct);
        if(stockProduct.getId() != null){
            Log.show("DATABASE", "StockProduct", "An error has occurred.\nStockProduct <id = "
                    + stockProduct.getId() +
                    "has not been removed.");
        }
        else {
            Log.show("DATABASE", "StockProduct", "The stock product has been removed.");
        }
    }

    public static void updateStockProduct(StockProduct stockProduct){
        dbManager.update(stockProduct);
        Log.show("DATABASE", "StockProduct", "The stock product has been updated.");
    }

    public static StockProduct getStockProduct(StockProduct stockProduct){
        return dbManager.get(StockProduct.class, stockProduct.getId());
    }

    public List<StockProduct> listAll(){
        return dbManager.list(StockProduct.class);
    }
}
