<<<<<<< HEAD
package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.SaleBudget;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by: Dario
 * On: 18/10/2017
 * */

public class SaleBudgetManager {

    private static DBManager dbManager = new DBConnection().manager();

    public Integer addSaleBudget(@NotNull SaleBudget saleBudget){
        Integer id = dbManager.add(saleBudget);

        if(saleBudget.getId() != null){
            Log.show("DATABASE", "SaleBudget", "SaleBudget <id = " + saleBudget.getId() + "> has been added to the database.");
        }
        return id;
    }

    public static void updateSaleBudget(@NotNull SaleBudget saleBudget){
        dbManager.update(saleBudget);
        Log.show("DATABASE", "SaleBudget", "The devolution has been updated.");
    }

    public static void removeSaleBudget(@NotNull SaleBudget saleBudget){
        Integer idBackup = saleBudget.getId();
        dbManager.remove(saleBudget);

        if(idBackup == null){
            Log.show("DATABASE", "SaleBudget", "An error has occurred. \n SaleBudget <id = " + saleBudget.getId() + "> has not been removed.");
        }
        else{
            Log.show("DATABASE", "SaleBudget", "The devolution has been removed.");
        }
    }

    public static SaleBudget getSaleBudget(@NotNull int id){
        return dbManager.get(SaleBudget.class, id);
    }

    public static List<SaleBudget> getAll(){
        return dbManager.list(SaleBudget.class);
    }

}
=======
package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.SaleBudget;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by: Dario
 * On: 18/10/2017
 * */

public class SaleBudgetManager {

    private static DBManager dbManager = new DBConnection().manager();

    public Integer addSaleBudget(@NotNull SaleBudget saleBudget){
        Integer id = dbManager.add(saleBudget);

        if(saleBudget.getId() != null){
            Log.show("DATABASE", "SaleBudget", "SaleBudget <id = " + saleBudget.getId() + "> has been added to the database.");
        }
        return id;
    }

    public static void updateSaleBudget(@NotNull SaleBudget saleBudget){
        dbManager.update(saleBudget);
        Log.show("DATABASE", "SaleBudget", "The devolution has been updated.");
    }

    public static void removeSaleBudget(@NotNull SaleBudget saleBudget){
        Integer idBackup = saleBudget.getId();
        dbManager.remove(saleBudget);

        if(idBackup == null){
            Log.show("DATABASE", "SaleBudget", "An error has occurred. \n SaleBudget <id = " + saleBudget.getId() + "> has not been removed.");
        }
        else{
            Log.show("DATABASE", "SaleBudget", "The devolution has been removed.");
        }
    }

    public static SaleBudget getSaleBudget(@NotNull int id){
        return dbManager.get(SaleBudget.class, id);
    }

    public static List<SaleBudget> getAll(){
        return dbManager.list(SaleBudget.class);
    }

}
>>>>>>> [C]ObservableCustomer
