package scgipp.service.customer_management;

import scgipp.data.hibernate.dao.CustomerDAO;
import scgipp.system.log.Log;

import java.util.List;

public class CustomerManager {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void register(Customer customer){
        customerDAO.add(customer);
        Log.show(Log.Type.INFO, "Customer \"" + customer.getName() + "\" added to database.");
    }

    public Customer findCustomer(Integer id) {
        return customerDAO.get(id);
    }

    public Customer findCustomer(String cpf) {

        for(Customer c : customerDAO.list()) {
            if (c.getCpf().equals(cpf))
                return c;
        }

        return null;

    }

    public void remove(Integer id) {
        customerDAO.remove(id);
        Log.show(Log.Type.INFO, "Customer \"" + id + "\" removed from database.");
    }

    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    public List<Customer> getAll() {
        return customerDAO.list();
    }

}
