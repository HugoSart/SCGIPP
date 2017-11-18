<<<<<<< HEAD
package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Supplier;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by: Dario
 * On: 6/10/17
 **/

public class SupplierManager {

    private static DBManager dbManager= DBConnection.manager();

    public static Integer addSupplier(@NotNull Supplier supplier){
        Integer id = dbManager.add(supplier);

        if(supplier.getId() != null){
            Log.show("DATABASE", "Supplier", "Supplier <id = " + supplier.getId() + "> has been added to the database.");
        }
        return id;
    }

    public static void removeSupplier(@NotNull Supplier supplier){
        Integer idBackup = supplier.getId();
        dbManager.remove(supplier);

        if(idBackup == null){
            Log.show("DATABASE", "Supplier", "An error has been ocurred.\nSupplier <id = " + supplier.getId() + "> has not been removed.");
        }
        else{
            Log.show("DATABASE", "Supplier", "The supplier has been removed.");
        }
    }

    public static void updateSupplier(@NotNull Supplier supplier){
        dbManager.update(supplier);
        Log.show("DATABASE", "Supplier", "The supplier has been removed.");
    }

    public static Supplier getSupplier(@NotNull int id){
        return dbManager.get(Supplier.class, id);
    }

    public static List<Supplier> getAll(){
        return dbManager.list(Supplier.class);
    }

}
=======
package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Supplier;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by: Dario
 * On: 6/10/17
 **/

public class SupplierManager {

    private static DBManager dbManager= DBConnection.manager();

    public static Integer addSupplier(@NotNull Supplier supplier){
        Integer id = dbManager.add(supplier);

        if(supplier.getId() != null){
            Log.show("DATABASE", "Supplier", "Supplier <id = " + supplier.getId() + "> has been added to the database.");
        }
        return id;
    }

    public static void removeSupplier(@NotNull Supplier supplier){
        Integer idBackup = supplier.getId();
        dbManager.remove(supplier);

        if(idBackup == null){
            Log.show("DATABASE", "Supplier", "An error has been ocurred.\nSupplier <id = " + supplier.getId() + "> has not been removed.");
        }
        else{
            Log.show("DATABASE", "Supplier", "The supplier has been removed.");
        }
    }

    public static void updateSupplier(@NotNull Supplier supplier){
        dbManager.update(supplier);
        Log.show("DATABASE", "Supplier", "The supplier has been removed.");
    }

    public static Supplier getSupplier(@NotNull int id){
        return dbManager.get(Supplier.class, id);
    }

    public static List<Supplier> getAll(){
        return dbManager.list(Supplier.class);
    }

}
>>>>>>> [C]ObservableCustomer
