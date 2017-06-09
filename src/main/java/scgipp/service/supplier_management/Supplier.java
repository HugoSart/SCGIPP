package scgipp.service.supplier_management;

import scgipp.service.Person;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by carloskanda on 13/05/17.
 */
@Entity
public class Supplier extends Person {

    public Supplier(){}
    public Supplier(Type type, String name, String cpf, LocalDate date) {
        super(type,name,cpf,date);
    }
}
