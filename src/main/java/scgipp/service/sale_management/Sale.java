package scgipp.service.sale_management;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import scgipp.service.product_management.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sale{
/**
 *  Implementação da classe vendas
 *
 */

    @Id
    @GeneratedValue
    private Integer id_venda;

    @Column
    private int id_cliente;

    @Column
    private int id_vendedor;

    @Column
    private long valor_total;

    @Column
    private Date data;

    @Column
    private Boolean concluido;

    @OneToMany
    private List<Product> lista_produtos = new ArrayList<>();

    public Sale(int cod_cliente, Date data_inicio, int cod_usuario )
    {
        this.id_cliente = cod_cliente;
        this.data = data_inicio;
        this.id_vendedor = cod_usuario;
    }

    public Sale(){}

    public int getIdCliente()
    {
        return(this.id_cliente);
    }

    public void setIdCliente(int novo_id)
    {
        this.id_cliente = novo_id;
    }

    public void setIdVenda(Integer id)
    {
        this.id_venda = id;
    }

    public Integer getIdVend()
    {
        return (this.id_venda);
    }

    public void setIdVendedor(int id)
    {
        this.id_vendedor = id;
    }

    public int getIdVendedor()
    {
        return (this.id_vendedor);
    }

    public void setValorTotal(long value)
    {
        this.valor_total = value;
    }

    public long getValorTotal()
    {
        return (this.valor_total);
    }

    public Date getData()
    {
        return this.data;
    }

    public void setConcluido(boolean atual_estado)
    {
        this.concluido = atual_estado;
    }

    public boolean getConcluido()
    {
        return this.concluido;
    }

    public void addProduto(Product novo_produto)
    {
        lista_produtos.add(novo_produto);
    }

    public List<Product> getProdutos()
    {
        return this.lista_produtos;
    }

    public SimpleIntegerProperty idVendaProperty()
    {
        return new SimpleIntegerProperty(id_venda);
    }

    public SimpleIntegerProperty idClienteProperty()
    {
        return new SimpleIntegerProperty(id_cliente);
    }

    public SimpleIntegerProperty idVendedorProperty()
    {
        return new SimpleIntegerProperty(id_vendedor);
    }

    public SimpleStringProperty dateProperty()
    {
        return new SimpleStringProperty(data.toString());
    }

    public SimpleLongProperty valorTotalProperty()
    {
        return new SimpleLongProperty(valor_total);
    }

    public SimpleBooleanProperty concluidoProperty()
    {
        return new SimpleBooleanProperty(concluido);
    }
}
