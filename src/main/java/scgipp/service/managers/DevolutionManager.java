package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Devolution;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by: Dario
 * On: 18/10/2017
 **/

public class DevolutionManager {

    private static DBManager dbManager = new DBConnection().manager();

    public static Integer addDevolution(@NotNull Devolution devolution){
        Integer id = dbManager.add(devolution);

        if(devolution.getId() != null){
            Log.show("DATABASE", "Devolution", "Devolution <id = " + devolution.getId() + "> has been added to the database.");
        }
        return id;
    }

    public static void removeDevolution(@NotNull Devolution devolution){
        Integer idBackup = devolution.getId();
        dbManager.remove(devolution);

        if(idBackup == null){
            Log.show("DATABASE", "Devolution", "An error has occurred. \n Devolution <id = " + devolution.getId() + "has not been removed.");
        }
        else{
            Log.show("DATABASE", "Devolution", "The devolution has been removed.");
        }
    }

    public static void updateDevolution(@NotNull Devolution devolution){
        dbManager.update(devolution);
        Log.show("DATABASE", "Devolution", "The devolution has been updated.");
    }

    public static Devolution getDevolution(@NotNull int id){
        return dbManager.get(Devolution.class, id);
    }

    public static List<Devolution> getAll(){
        return dbManager.list(Devolution.class);
    }

}
