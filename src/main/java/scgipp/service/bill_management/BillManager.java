package scgipp.service.bill_management;

import scgipp.data.hibernate.dao.BillDAO;
import scgipp.data.hibernate.dao.SaleDAO;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;
import scgipp.service.sale_management.Sale;
import scgipp.system.log.Log;

import java.util.List;

import static scgipp.service.sale_management.Sale.PAID;

/**
 * Created by kira on 01/08/17.
 */
public class BillManager
{
    private BillDAO billDAO = new BillDAO();

    public void registerBill(Bill newBill)
    {
        billDAO.add(newBill);
        Log.show(Log.Type.INFO, "New Bill Registred.");
    }

    public void confirmBill(Bill billAlvo)
    {
        billAlvo.status = PAID;

        billDAO.update(billAlvo);
    }

    public Bill findBill(Integer idAlvo)
    {
        return billDAO.get(idAlvo);
    }

    public void remove(Integer idAlvo)
    {
        billDAO.remove(idAlvo);
        Log.show(Log.Type.INFO, "Bill \"" + idAlvo + "\" removed from database.");
    }

    public List<Bill> getAll()
    {
        return billDAO.list();
    }

}
