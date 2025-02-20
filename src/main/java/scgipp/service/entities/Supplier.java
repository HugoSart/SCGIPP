package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
import scgipp.service.entities.superclass.Person;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

/**
* Created by: Dario
* On: 4/10/17
**/

@Entity
public class Supplier extends Person {

    public Supplier() {}

    public Supplier(String name, String cnpj) {
        setType(Type.LEGAL);
        setName(name);
        setCpf_cnpj(cnpj);
        setDate(LocalDate.now());
    }

    public Supplier(String name, String cnpj, List<Address> addresses, List<Phone> phones) {
        this(name, cnpj);
        this.embeddableAddresses = addresses;
        this.phones = phones;
    }
}

