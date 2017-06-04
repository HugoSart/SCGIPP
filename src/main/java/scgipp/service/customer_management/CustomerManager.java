package scgipp.service.customer_management;

import scgipp.service.user_management.User;

import java.util.List;

public class CustomerManager {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void register(Customer customer){
        customerDAO.add(customer);
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
    }

    public List<Customer> getAll() {
        return customerDAO.list();
    }

}
