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
 * Created by: Anderson Dario
 * On: 15/10/2017
 * */

public class SupplierManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();
    }

    @Test
    public void addSupplierTest(){
        Supplier supplier1 = new Supplier("Empresa 1", "44479557000122", null, null);
        Supplier supplier2 = new Supplier("Empresa 2", "24209266000107", null, null);

        Integer idTest1 = SupplierManager.addSupplier("Empresa 1", "44479557000122", null, null);
        Integer idTest2 = SupplierManager.addSupplier("Empresa 2", "24209266000107", null, null);

        Assert.assertEquals(idTest1, supplier1.getId());
        Assert.assertEquals(idTest2, supplier2.getId());
    }

    @Test
    public void updateSupplierTest(){
        Supplier supplier = SupplierManager.getSupplier(1);

        SupplierManager.updateSupplier(supplier, "Empresa Alterada", null, null);

        Assert.assertEquals(SupplierManager.getSupplier(1).getName(), "Empresa Alterada");
    }
}
