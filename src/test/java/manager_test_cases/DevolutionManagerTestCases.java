package manager_test_cases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.entities.Devolution;
import scgipp.service.entities.Product;
import scgipp.service.entities.Sale;
import scgipp.service.entities.SaleBudget;
import scgipp.service.managers.DevolutionManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.SaleManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class DevolutionManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();
    }

    @Test
    public void addDevolutionTest(){
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        Product product1 = new Product("name1", "description1", 4, new BigDecimal(4), Double.doubleToLongBits(3.5));
        Product product2 = new Product("name2", "description2", 4, new BigDecimal(4), Double.doubleToLongBits(3.5));

        SaleManager saleManager = new SaleManager();
        ProductManager productManager = new ProductManager();

        saleManager.addSale(sale1);
        saleManager.addSale(sale2);
        productManager.addProduct(product1);
        productManager.addProduct(product2);

        Devolution devolution1 = new Devolution(sale1, product1, new Date(), true);
        Devolution devolution2 = new Devolution(sale1, product1, new Date(), true);
        Devolution devolution3 = new Devolution(sale2, product2, new Date(), true);

        int id1 = DevolutionManager.addDevolution(devolution1);
        int id2 = DevolutionManager.addDevolution(devolution2);
        int id3 = DevolutionManager.addDevolution(devolution3);

        Assert.assertEquals(id1, (int)devolution1.getId());
        Assert.assertEquals(id2, (int)devolution2.getId());
        Assert.assertEquals(id3, (int)devolution3.getId());
    }

    @Test
    public void removeDevolution(){
        ProductManager productManager = new ProductManager();
        SaleManager saleManager = new SaleManager();
        Sale sale = new Sale();
        Product product = new Product("name3", "description3", 4, new BigDecimal(5), Double.doubleToLongBits(4.4));
        Devolution devolution = new Devolution(sale, product, new Date(), true);

        saleManager.addSale(sale);
        productManager.addProduct(product);
        devolution.setSale(sale);
        devolution.setProduct(product);
        Integer idTest = DevolutionManager.addDevolution(devolution);

        DevolutionManager.removeDevolution(devolution);
        Assert.assertNull(DevolutionManager.getDevolution(idTest.intValue()));
    }

}
