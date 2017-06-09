package scgipp.service.customer_management;

import scgipp.service.Person;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by carloskanda on 13/05/17.
 */
@Entity
public class Customer extends Person {

    public Customer() {

    }

    public Customer(Type type, String name, String cpf, LocalDate date) {
        super(type, name, cpf, date);
    }

}