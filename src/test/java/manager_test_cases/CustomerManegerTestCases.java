package manager_test_cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.entities.Customer;
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.CustomerManager;

import java.time.LocalDate;

/**
 * Created by Kira in 07/10
 */
public class CustomerManegerTestCases {

    @Before
    public void setUp() {
        DBConnection.initialize();
        DBConnection.manager().add(new Customer(Person.Type.LEGAL , "customerTest", "000000", LocalDate.now()));
    }

    @Test
    public void addCustomerTest() {

        Customer customerTest = new Customer(Person.Type.LEGAL, "customerTest2", "111111", LocalDate.now());
        DBConnection.manager().add(customerTest);
        Integer id = customerTest.getId();

        Assert.assertEquals((int)id, (int)customerTest.getId());

    }

    @Test
    public void updateUserTest() {

        Integer auxId = 1;

        Customer customerUpdate = DBConnection.manager().get(Customer.class, auxId);
        customerUpdate.setName("TestName");

        CustomerManager.updateCustomer(customerUpdate);
        customerUpdate = DBConnection.manager().get(Customer.class, customerUpdate.getId());
        Assert.assertEquals(customerUpdate.getName(), "TestName");

    }

    @After
    public void tearDown() {
        //DBConnection.exit();
    }

}
