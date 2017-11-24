package manager_test_cases;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.entities.Supplier;
import scgipp.service.managers.SupplierManager;

import java.util.ArrayList;

/**
 * Created by: Dario
 * On: 15/10/2017
 * */

public class SupplierManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();
    }

    @Test
    public void addSupplierTest(){
        Supplier supplier1 = new Supplier("Empresa 1", "000000111000", null, null);
        Supplier supplier2 = new Supplier("Empresa 2", "000000222000", null, null);

        Integer idTest1 = SupplierManager.addSupplier(supplier1);
        Integer idTest2 = SupplierManager.addSupplier(supplier2);

        Assert.assertEquals(idTest1, supplier1.getId());
        Assert.assertEquals(idTest2, supplier2.getId());
    }

    @Test
    public void removeSupplierTest(){
        Supplier supplier = new Supplier("Empresa 3", "000000333000", new ArrayList<>(), new ArrayList<>());
        Integer idTest = SupplierManager.addSupplier(supplier);

        SupplierManager.removeSupplier(supplier);
        supplier = SupplierManager.getSupplier(idTest.intValue());

        Assert.assertNull(supplier);
    }

    @Test
    public void updateSupplierTest(){
        Supplier supplier = SupplierManager.getSupplier(1);
        supplier.setName("Empresa Alterada");

        SupplierManager.updateSupplier(supplier);

        Assert.assertEquals(SupplierManager.getSupplier(1).getName(), "Empresa Alterada");
    }

}
