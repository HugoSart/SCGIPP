package scgipp.service.sale_management;

import scgipp.data.hibernate.dao.ProductDAO;
import scgipp.data.hibernate.dao.SaleDAO;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;
import scgipp.system.log.Log;

import java.util.List;

import static scgipp.service.sale_management.Sale.PAID;

/**
 * Created by kira on 31/07/17.
 */
public class SaleManager {

    private SaleDAO saleDAO = new SaleDAO();

    public void registerSale(Sale sale) {
        sale.status = Sale.PENDING;
        saleDAO.add(sale);
        Log.show(Log.Type.INFO, "Venda registrada.");
    }

    public void confirmSale(Sale sale) {

        sale.status = PAID;

        for (Product product : sale.saleBudget.products) {

            List<Product> estoque = (new ProductManager()).getAll();

            for (Product e : estoque) {
                if (product.getId() == e.getId()) {
                    e.setAmout(e.getAmount() - 1);
                    (new ProductManager()).update(e);
                }
            }

        }

        saleDAO.update(sale);

    }

    public List<Sale> getAll() {
        return saleDAO.list();
    }

}
