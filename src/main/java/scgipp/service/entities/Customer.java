package scgipp.service.entities;

import br.com.uol.pagseguro.domain.Address;
import scgipp.data.hibernate.Entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kira on 06/10
 */

@javax.persistence.Entity
public class Customer extends Entity {

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private Date date;

    @Column(unique = true)
    private String cpf;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Tipo tipo;

    protected Customer() {}

    protected Customer(String name, Date date, String cpf, Address address,
                       String phone, Tipo tipo)
    {
        this();
        setName(name);
        setDate(date);
        setCpf(cpf);
        setAddress(address);
        setPhone(phone);
        setTipo(tipo);

    }

    public void setId(Integer id) {this.id = id;}

    public Integer getId() {return id;}

    public void setName(String name) {this.name = name; }

    public String getName() {return name;}

    public void setDate(Date date) {this.date = date;}

    public Date getDate() {return this.date;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getCpf() {return cpf;}

    public void setAddress(Address address) {this.address = address;}

    public Address getAddress() {return address;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getPhone() {return phone;}

    public void setTipo(Tipo tipo) {this.tipo = tipo;}

    public Tipo getTipo() {return this.tipo;}

    public String toString() {return "id = " + id + "name = " + name + "cpf = " + cpf  + ";\n";}

}
