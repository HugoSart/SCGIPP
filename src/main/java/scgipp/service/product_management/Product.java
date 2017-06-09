package scgipp.service.product_management;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kira on 05/06/17.
 */

/**
     * Implementacao de produtos
     *
     * Define a classe utilizada para representar os produtos do sistema
     * Foi preferivel nao extender esta classe a Person
     *
     * @atributos:
     * id: chave primaria, identificador do produto
     * type: indica o type de produto
     * count: armazena a quantidade deste produto buyable em estoque
     * name: nome do produto
     * descricao: Breve descricao do produto
     * customerPrice: preco de venda do produto
     * buyable: indica se ha unidades disponiveis para venda
 */
@Entity
public class Product {


    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private int type;

    @Column
    private int amount;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private double customerPrice;

    @Column
    private boolean buyable;

    /**
             * Metodo construtor da classe
             *
             * @args:
             * prodTipo: e o type do produto
             * prodUn: unidades em estoque
             * prodNome: nome do produto
             * prodDesc: breve descricao do produto
             * prodValue: valor para venda do produto
     */
    public Product(int prodTipo, int prodUn, String prodNome, String prodDesc, double prodValue, boolean isBuyable){


            this.type = prodTipo;
            this.amount = prodUn;
            this.name = prodNome;
            this.description = prodDesc;
            this.customerPrice = prodValue;
            this.buyable = isBuyable;

    }

    public Product(){}

    /**
         * Metodo para retornar id do produto
         *
         * @returns:
         * retorna o id
         */
    public int getId(){


        return (this.id);
    }

    /**
         * Metodo para setar o numero de unidades disponiveis em estoque
         *
         * @args:
         * nroUnidades: qtde de itens a ser adicionado ao estoque
         */
    public void setAmout(int nroUnidades){


        this.amount = nroUnidades;
    }

    public int getAmount(){
        /*
         * Metodo pra retornar nro de itens deste produto em estoque
         *
         * @returns:
         * nro de unidades disponiveis
         */

        return (this.amount);
    }
    public String getName(){
        /*
         * Metodo para pegar o nome do produto
         *
         * @returns:
         * retorna o nome do produto
         */

        return (this.name);
    }

    public String getDescription(){
        /*
         * Metodo para retornar a descricao deste produto
         *
         * @returns:
         * retorna uma stirng, com a descricao do produto
         */

        return (this.description);
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCustomerPrice(double novoValor){
        /*
         * Metodo para setar novo valor de venda do produto
         *
         * @args:
         * novoValor: o novo preco a ser setado
         */

        this.customerPrice = novoValor;
    }

    public double getCustomerPrice(){
        /*
         * Metodo para retornar o preco do produto
         *
         * @returns:
         * retorna o atual valor do produto
         */

        return this.customerPrice;
    }

    public boolean isBuyable(){
        return buyable;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleIntegerProperty typeProperty() {
        return new SimpleIntegerProperty(type);
    }

    public SimpleIntegerProperty amountProperty() {
        return new SimpleIntegerProperty(amount);
    }

    public SimpleStringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }

    public SimpleDoubleProperty customerPriceProperty() {
        return new SimpleDoubleProperty(customerPrice);
    }

}
