package scgipp.service.sale_management;

import scgipp.data.hibernate.dao.SaleBudgetDAO;
import scgipp.data.hibernate.dao.SaleDAO;

import java.util.List;

public class SaleBudgetManager {

    private SaleBudgetDAO saleBudgetDAO = new SaleBudgetDAO();

    public void saveBudget(SaleBudget saleBudget) {
        saleBudgetDAO.add(saleBudget);
    }

    public void removeBudget(Integer id) {
        saleBudgetDAO.remove(id);
    }

    public List<SaleBudget> getAll() {
        return saleBudgetDAO.list();
    }

}
