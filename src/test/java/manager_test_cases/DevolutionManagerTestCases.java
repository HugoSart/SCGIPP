<<<<<<< HEAD
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
import scgipp.service.managers.SaleManager;

import java.time.LocalDate;

public class DevolutionManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();
    }

    @Test
    public void addDevolutionTest(){
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        SaleManager saleManager = new SaleManager();

        saleManager.addSale(sale1);
        saleManager.addSale(sale2);

        Devolution devolution1 = new Devolution(sale1);
        Devolution devolution2 = new Devolution(sale1);
        Devolution devolution3 = new Devolution(sale2);

        int id1 = DevolutionManager.addDevolution(devolution1);
        int id2 = DevolutionManager.addDevolution(devolution2);
        int id3 = DevolutionManager.addDevolution(devolution3);

        Assert.assertEquals(id1, (int)devolution1.getId());
        Assert.assertEquals(id2, (int)devolution2.getId());
        Assert.assertEquals(id3, (int)devolution3.getId());
    }

    @Test
    public void removeDevolution(){
        SaleManager saleManager = new SaleManager();
        Sale sale = new Sale();
        Devolution devolution = new Devolution(sale);

        saleManager.addSale(sale);
        devolution.setSale(sale);
        Integer idTest = DevolutionManager.addDevolution(devolution);

        DevolutionManager.removeDevolution(devolution);
        Assert.assertNull(DevolutionManager.getDevolution(idTest.intValue()));
    }

}
=======
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
import scgipp.service.managers.SaleManager;

import java.time.LocalDate;

public class DevolutionManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();
    }

    @Test
    public void addDevolutionTest(){
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        SaleManager saleManager = new SaleManager();

        saleManager.addSale(sale1);
        saleManager.addSale(sale2);

        Devolution devolution1 = new Devolution(sale1);
        Devolution devolution2 = new Devolution(sale1);
        Devolution devolution3 = new Devolution(sale2);

        int id1 = DevolutionManager.addDevolution(devolution1);
        int id2 = DevolutionManager.addDevolution(devolution2);
        int id3 = DevolutionManager.addDevolution(devolution3);

        Assert.assertEquals(id1, (int)devolution1.getId());
        Assert.assertEquals(id2, (int)devolution2.getId());
        Assert.assertEquals(id3, (int)devolution3.getId());
    }

    @Test
    public void removeDevolution(){
        SaleManager saleManager = new SaleManager();
        Sale sale = new Sale();
        Devolution devolution = new Devolution(sale);

        saleManager.addSale(sale);
        devolution.setSale(sale);
        Integer idTest = DevolutionManager.addDevolution(devolution);

        DevolutionManager.removeDevolution(devolution);
        Assert.assertNull(DevolutionManager.getDevolution(idTest.intValue()));
    }

}
>>>>>>> [C]ObservableCustomer
