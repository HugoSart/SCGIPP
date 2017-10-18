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
    public void updateDevolutionTest(){
        Devolution devolution = DevolutionManager.getDevolution(1);
        Sale sale = new Sale();

        sale.setId(9999999);
        devolution.setSale(sale);

        DevolutionManager.updateDevolution(devolution);

        devolution = DevolutionManager.getDevolution(1);
        int saleIdTest = devolution.getSale().getId();

        Assert.assertEquals(saleIdTest, 9999999);
    }

    @Test
    public void removeDevolution(){
        Sale sale = new Sale();
        Devolution devolution = new Devolution(sale);

        DevolutionManager.removeDevolution(devolution);
        DevolutionManager.updateDevolution(devolution);
    }

}
