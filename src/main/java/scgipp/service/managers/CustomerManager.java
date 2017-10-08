package scgipp.service.managers;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Customer;
import scgipp.system.log.Log;

/**
 * Created by kira in 06/10
 */

public class CustomerManager {

    private static DBManager dbManager = DBConnection.manager();

    public static Integer addCustomer(Customer customer)
    {
        Integer id = dbManager.add(customer);
        if (customer.getId() != null)
            Log.show("DATABASE", "Customer", "Customer id = " + customer.getId() +
                     "name = " + customer.getName() + "cpf = " + customer.getCpf() + "added to scgipp db");
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

    public static Customer getCustomer( Integer id)
    {
        return dbManager.get( Customer.class ,id);
    }

}
