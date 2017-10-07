package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Supplier;

import java.util.List;

/**
 * Created by: Dario
 * On: 6/10/17
 **/

public class SupplierManager {

    private static DBManager dbManager= new DBConnection().manager();

    public void addSupplier(@NotNull Supplier supplier){
        dbManager.add(supplier);
    }

    public void updateSupplier(@NotNull Supplier supplier){
        dbManager.update(supplier);
    }

    public void removeSupplier(@NotNull Supplier supplier){
        dbManager.remove(supplier.getId());
    }

    public List<Supplier> getAll(){
        return dbManager.list(Supplier.class);
    }

}
