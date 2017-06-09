package scgipp.service.supplier_management;

import scgipp.data.hibernate.dao.SupplierDAO;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by carloskanda on 13/05/17.
 */
public class SupplierManager {

    private SupplierDAO supplierDAO = new SupplierDAO();

    public void register(Supplier supplier) {
        supplierDAO.add(supplier);
        Log.show(Log.Type.INFO, "Supplier \"" + supplier.getName() + "\" added to database.");
    }

    public void remove(Supplier supplier) {
        supplierDAO.remove(supplier.getId());
        Log.show(Log.Type.INFO, "Supplier \"" + supplier.getName() + "\" removed from database.");
    }

    public List<Supplier> getAll() {
        return supplierDAO.list();
    }

}
