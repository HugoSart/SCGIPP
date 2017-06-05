package scgipp.service.product_management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kira on 05/06/17.
 */
@Entity
public class Product {
    /*
     * Implementacao de produtos
     *
     * Define a classe utilizada para representar os produtos do sistema
     * Foi preferivel nao extender esta classe a Person
     *
     * @atributos:
     * idProduto: chave primaria, identificador do produto
     * tipo: indica o tipo de produto
     * unEstoque: armazena a quantidade deste produto disponivel em estoque
     * pNome: nome do produto
     * descricao: Breve descricao do produto
     * pVenda: preco de venda do produto
     * disponivel: indica se ha unidades disponiveis para venda
     */

    @Id
    @GeneratedValue
    private int idProduto;

    @Column
    private int tipo;

    @Column
    private int unEstoque;

    @Column(unique = true)
    private String pNome;

    @Column
    private String descricao;

    @Column
    private double pVenda;

    @Column
    private boolean disponivel;

    public Product(int prodTipo, int prodUn, String prodNome,
                   String prodDesc, double prodValue){
            /*
             * Metodo construtor da classe
             *
             * @args:
             * prodTipo: e o tipo do produto
             * prodUn: unidades em estoque
             * prodNome: nome do produto
             * prodDesc: breve descricao do produto
             * prodValue: valor para venda do produto
             */

            this.tipo = prodTipo;
            this.unEstoque = prodUn;
            this.pNome = prodNome;
            this.descricao = prodDesc;
            this.pVenda = prodValue;
            this.disponivel = this.estaDisponivel();

    }
    //construtor padrao, nescessario para o hibernate
    public Product(){}

    public int getId(){
        /*
         * Metodo para retornar id do produto
         *
         * @returns:
         * retorna o id
         */

        return (this.idProduto);
    }

    public void setUnidades(int nroUnidades){
        /*
         * Metodo para setar o numero de unidades disponiveis em estoque
         *
         * @args:
         * nroUnidades: qtde de itens a ser adicionado ao estoque
         */

        this.unEstoque = nroUnidades;
    }

    public int getUnidades(){
        /*
         * Metodo pra retornar nro de itens deste produto em estoque
         *
         * @returns:
         * nro de unidades disponiveis
         */

        return (this.unEstoque);
    }
    public String getName(){
        /*
         * Metodo para pegar o nome do produto
         *
         * @returns:
         * retorna o nome do produto
         */

        return (this.pNome);
    }

    public String getDescricao(){
        /*
         * Metodo para retornar a descricao deste produto
         *
         * @returns:
         * retorna uma stirng, com a descricao do produto
         */

        return (this.descricao);
    }

    public void setPreco(double novoValor){
        /*
         * Metodo para setar novo valor de venda do produto
         *
         * @args:
         * novoValor: o novo preco a ser setado
         */

        this.pVenda = novoValor;
    }

    public double getPreco(){
        /*
         * Metodo para retornar o preco do produto
         *
         * @returns:
         * retorna o atual valor do produto
         */

        return this.pVenda;
    }

    public boolean estaDisponivel(){
        /*
         * Metodo que verifica se ha unidades disponiveis a venda deste produto em estoque
         *
         * @returns:
         * true caso haja, false caso contrario
         */

        return (this.getUnidades() != 0);
    }
}
