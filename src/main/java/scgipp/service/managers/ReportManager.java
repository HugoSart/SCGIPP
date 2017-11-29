package scgipp.service.managers;

import scgipp.service.entities.Customer;
import scgipp.service.entities.Product;
import scgipp.service.entities.Sale;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
/* created by kira
 * 24/11/17
 */
public class ReportManager {

    private Integer numberSales;
    private Integer numberClients;
    private BigDecimal grossProfit;
    private Integer numberItens;
    private LocalDate currentDate;

    public ReportManager(){
        SaleManager saleManager = new SaleManager();
        for (Sale sale : saleManager.getAll()){
            numberSales += 1;
            for (Product product: sale.productsList){
                numberItens += product.getQuantity();
            }
            grossProfit = getGrossProfit().add(sale.getTotalPrice());
        }
        CustomerManager customerManager = new CustomerManager();
        for (Customer customer: customerManager.getAll()){
            numberClients += 1;
        }
        currentDate = LocalDate.now();
    }

    public Integer getNumberClients() {
        return numberClients;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public Integer getNumberItens() {
        return numberItens;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Integer getNumberSales() {

        return numberSales;
    }
}
