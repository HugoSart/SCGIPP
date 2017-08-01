package scgipp.service.bill_management;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.service.Person;
import scgipp.service.customer_management.Customer;
import scgipp.service.supplier_management.Supplier;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kira on 01/08/17.
 */

@Entity
public class Bill
{
    public static final int PENDING = 0;
    public static final int PAID = 1;
    public static final int PAGAR = 1;
    public static final int RECEBER = 0;

    @Id
    public Integer bill_id;

    @OneToOne
    public Supplier a_pagar;

    @OneToOne
    public Customer a_receber;

    @Column
    public Date data_vencimento;

    @Column
    public int status;

    @Column
    public long valor_total;

    @Column
    public int tipo_conta;

    public Bill(){}

    public Bill(Integer codigo,Customer customer, Supplier supplier, Date data_pagamento, long total, int tipo)
    {
        this.bill_id = codigo;
        this.a_pagar = supplier;
        this.tipo_conta = tipo;
        this.valor_total = total;
        this.data_vencimento = data_pagamento;
        this.a_receber = customer;
    }

    public SimpleIntegerProperty billIdProperty()
    {
        return new SimpleIntegerProperty(bill_id);
    }

    public SimpleStringProperty supplierProperty()
    {
        return new SimpleStringProperty(a_pagar.getName());
    }

    public SimpleStringProperty customerProperty()
    {
        return new SimpleStringProperty(a_receber.getName());
    }

    public SimpleStringProperty dateProperty()
    {
        return new SimpleStringProperty(data_vencimento.toString());
    }

    public SimpleIntegerProperty statusProperty()
    {
        return new SimpleIntegerProperty(status);
    }

    public SimpleIntegerProperty tipoProperty()
    {
        return new SimpleIntegerProperty(tipo_conta);
    }
}
