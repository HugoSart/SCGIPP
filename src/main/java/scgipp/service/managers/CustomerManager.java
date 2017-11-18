<<<<<<< HEAD
package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Customer;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by kira in 06/10
 */

public class CustomerManager {

    private static CustomerManager instance = null;

    private static DBManager dbManager = DBConnection.manager();

    public static CustomerManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new CustomerManager();
        return instance;
    }

    public static Integer addCustomer(Customer customer)
    {
        Integer id = dbManager.add(customer);
        if (customer.getId() != null)
            Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                     "name = " + customer.getName() + "cpf = " + customer.getCpf_cnpj() + "added to scgipp db");
        return id;
    }

    public static void updateCustomer(Customer customer)
    {
        dbManager.update(customer);

        Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                 "name = " + customer.getName() + "has been updated in sgcipp db");

    }

    public static void removeCustomer(Customer customer)
    {
        dbManager.remove(customer);

        Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                 "has been removed from scgipp db");

    }

    public List<Customer> getAll() {
        return DBConnection.manager().list(Customer.class);
    }


    public static Customer getCustomer( Integer id)
    {
        return dbManager.get( Customer.class ,id);
    }

}
=======
package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Customer;
import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by kira in 06/10
 */

public class CustomerManager {

    private static CustomerManager instance = null;

    private static DBManager dbManager = DBConnection.manager();

    public static CustomerManager getInstance() {
        if (instance == null && DBConnection.isActive()) instance = new CustomerManager();
        return instance;
    }

    public static Integer addCustomer(Customer customer)
    {
        Integer id = dbManager.add(customer);
        if (customer.getId() != null)
            Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                     "name = " + customer.getName() + "cpf = " + customer.getCpf_cnpj() + "added to scgipp db");
        return id;
    }

    public static void updateCustomer(Customer customer)
    {
        dbManager.update(customer);

        Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                 "name = " + customer.getName() + "has been updated in sgcipp db");

    }

    public static void removeCustomer(Customer customer)
    {
        dbManager.remove(customer);

        Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                 "has been removed from scgipp db");

    }

    public List<Customer> getAll() {
        return DBConnection.manager().list(Customer.class);
    }


    public static Customer getCustomer( Integer id)
    {
        return dbManager.get( Customer.class ,id);
    }

}
>>>>>>> [C]ObservableCustomer
