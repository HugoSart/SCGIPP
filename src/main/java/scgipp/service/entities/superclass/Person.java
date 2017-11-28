package scgipp.service.entities.superclass;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsart on 13/05/17.
 */
/**
 * A classe pai deve ser do tipo MappedSuperClass
 * Dessa forma, as classes derivadas criam sua propria tabela
 * Ao inves de utilizar a tabela Person
 */

@MappedSuperclass
public class Person extends BaseEntity<Integer> {

    public enum Type {
        LEGAL("Jurídica"), PHYSICAL("Física");

        String name;

        Type(String name) {
            this.name = name;
        }

        public SimpleStringProperty nameProperty() {
            return new SimpleStringProperty(name);
        }

    }

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf_cnpj;

    @Column(nullable = false)
    private LocalDate date;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<Phone> phones;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<Address> embeddableAddresses;

    public Person() {}

    public Person(Type type, String name, String cpf_cnpj, LocalDate date) {
        this.type = type;
        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf) {
        this.cpf_cnpj = cpf;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        if (this.phones == null)
        {
            this.phones= new ArrayList<>();
        }        phones.add(phone);
    }

    public List<Address> getAddresses() {
        return embeddableAddresses;
    }

    public void addAdress(Address embeddableAddress) {
        if (this.embeddableAddresses == null)
        {
            this.embeddableAddresses = new ArrayList<>();
        }
        embeddableAddresses.add(embeddableAddress);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty typeProperty() {
        return type.nameProperty();
    }

    public SimpleStringProperty addressProperty() {
        String endereco = getAddresses().get(1).getStreet().toString();
        return new SimpleStringProperty(endereco);
    }

    public SimpleStringProperty phoneProperty() {
        String fone = getPhones().get(1).getNumber().toString();
        return new SimpleStringProperty(fone);
    }

    public SimpleStringProperty cpfProperty() {
        return new SimpleStringProperty(cpf_cnpj);
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[type=" + type + ", name=" + name + ", cpf_cnpj=" + cpf_cnpj + "]\n";
    }
}
