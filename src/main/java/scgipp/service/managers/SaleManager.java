package scgipp.service.managers;

import org.jetbrains.annotations.NotNull;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Sale;

import java.util.List;

public class SaleManager {

    private static DBManager dbManager = DBConnection.manager();

    public void addSale(@NotNull Sale sale){dbManager.add(sale);}

    public void updateSale(@NotNull Sale sale){dbManager.update(sale);}

    public void removeSale(@NotNull Sale sale){dbManager.remove(sale);}

    public List<Sale> getAll(){return dbManager.list(Sale.class);}

}
