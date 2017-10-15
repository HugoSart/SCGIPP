package scgipp.service.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.service.entities.embbeded.EmbeddableAddress;
import scgipp.service.entities.superclass.Person;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kira on 06/10
 */

@Entity
public class Customer extends Person {

    protected Customer() {}

    public Customer(Type type, String name, String cpf, LocalDate date) {
        setType(type);
        setName(name);
        setCpf(cpf);
        setDate(date);

    }

}
