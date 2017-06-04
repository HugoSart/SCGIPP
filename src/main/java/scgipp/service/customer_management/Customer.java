package scgipp.service.customer_management;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Customer  {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String cpf;

    private Calendar date;

    @ElementCollection
    public List<String> phones;

    @ElementCollection
    public List<Adress> adresses;

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

    public Customer() {}

    public Customer(Type type, String name, String cpf, Calendar date) {
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
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