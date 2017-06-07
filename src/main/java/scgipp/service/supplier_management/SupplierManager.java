package scgipp.service.supplier_management;

import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by hugo_ on 05/06/2017.
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
