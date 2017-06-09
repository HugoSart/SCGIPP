package scgipp.service;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
public abstract class Person {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate date;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<String> phones = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    public List<Adress> adresses = new ArrayList<>();

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

    public Person() {}

    public Person(Type type, String name, String cpf, LocalDate date) {
        this.type = type;
        this.name = name;
        this.cpf = cpf;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void addPhone(String phone) {
        phones.add(phone);
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void addAdress(Adress adress) {
        adresses.add(adress);
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

    public SimpleStringProperty typeProperty() {
        return type.nameProperty();
    }

    public SimpleStringProperty cpfProperty() {
        return new SimpleStringProperty(cpf);
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date.toString());
    }

}
