package scgipp.service.entities;

import scgipp.service.entities.superclass.Person;


import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by kira on 06/10
 */

@Entity
public class Customer extends Person {

    protected Customer() {}

    public Customer(Type type, String name, String cpf, LocalDate date) {
        setType(type);
        setName(name);
        setCpf_cnpj(cpf);
        setDate(date);

    }

}
