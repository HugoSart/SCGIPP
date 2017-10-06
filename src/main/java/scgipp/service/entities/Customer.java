package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Address;
import scgipp.data.hibernate.Entity;
import scgipp.service.entities.embbeded.EmbeddableAddress;
import scgipp.service.entities.superclass.Person;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by kira on 06/10
 */

@javax.persistence.Entity
public class Customer extends Person {


    protected Customer() {}

    protected Customer(String name, LocalDate date, String cpf, EmbeddableAddress address,
                       String phone, Person.Type type)
    {
        setName(name);
        setDate(date);
        setCpf(cpf);
        addAdress(address);
        addPhone(phone);
        setType(type);

    }


}
