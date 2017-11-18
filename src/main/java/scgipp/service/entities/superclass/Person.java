<<<<<<< HEAD
package scgipp.service.entities.superclass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.data.hibernate.BaseEntity;
import scgipp.service.entities.embbeded.EmbeddableAddress;
import scgipp.service.entities.embbeded.EmbeddablePhone;

import javax.persistence.*;
import java.time.LocalDate;
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
public class Person extends BaseEntity {

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
    public List<EmbeddablePhone> phones;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<EmbeddableAddress> embeddableAddresses;

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

    public List<EmbeddablePhone> getPhones() {
        return phones;
    }

    public void setPhones(List<EmbeddablePhone> phones) {
        this.phones = phones;
    }

    public void addPhone(EmbeddablePhone phone) {
        phones.add(phone);
    }

    public List<EmbeddableAddress> getAddresses() {
        return embeddableAddresses;
    }

    public void addAdress(EmbeddableAddress embeddableAddress) {
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
=======
package scgipp.service.entities.superclass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import scgipp.data.hibernate.BaseEntity;
import scgipp.service.entities.embbeded.EmbeddableAddress;
import scgipp.service.entities.embbeded.EmbeddablePhone;

import javax.persistence.*;
import java.time.LocalDate;
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
public class Person extends BaseEntity {

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
    public List<EmbeddablePhone> phones;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<EmbeddableAddress> embeddableAddresses;

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

    public List<EmbeddablePhone> getPhones() {
        return phones;
    }

    public void setPhones(List<EmbeddablePhone> phones) {
        this.phones = phones;
    }

    public void addPhone(EmbeddablePhone phone) {
        phones.add(phone);
    }

    public List<EmbeddableAddress> getAddresses() {
        return embeddableAddresses;
    }

    public void addAdress(EmbeddableAddress embeddableAddress) {
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
>>>>>>> [C]ObservableCustomer
