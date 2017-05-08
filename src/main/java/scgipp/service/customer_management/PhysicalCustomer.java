/*package scgipp.service.customer_management;


//fisica
//firstName, lastName, cpf, sexo, dataNascimento,

import javax.persistence.Column;

public class PhysicalCustomer extends Customer {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String sexo;
    @Column(nullable = false)
    private String dataNascimento;

    private PhysicalCustomer(){

    }

    public abstract Customer createCustomer(){

    }

    public abstract boolean deleteCustomer(){

    }

}
*/