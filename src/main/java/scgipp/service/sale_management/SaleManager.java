package scgipp.service.sale_management;

import scgipp.data.hibernate.dao.SaleDAO;
import scgipp.service.product_management.Product;
import scgipp.system.log.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by kira on 31/07/17.
 */
public class SaleManager
{

    SaleDAO saleDAO = new SaleDAO();

    public SaleManager()
    {
        /*
         * Metodo construtor da classe
         */
    }

    public Sale register(int cliente_venda, Date data_venda, int usuario_venda) {

        Sale novaSale = new Sale(cliente_venda, data_venda, usuario_venda);
        try
        {
            saleDAO.add(novaSale);
        }
        catch (ExceptionInInitializerError error)
        {
            error.printStackTrace();
            Log.show(Log.Type.INFO, "Sale: \"" + cliente_venda + "\" registred fail.");
        }
        Log.show(Log.Type.INFO, "Sale: \"" + cliente_venda + "\" successufly registred.");

        return novaSale;
    }

    public void remove(Integer idAlvo) {
        /*
         * Metodo para exclusao de cadastro com base no id
         *
         * @args:
         * idAlvo: e o id do produto a ser excluido
         */
        try
        {
            saleDAO.remove(idAlvo);
        }
        catch (ExceptionInInitializerError error)
        {
            error.printStackTrace();
        }
        Log.show(Log.Type.INFO, "Sale ID: " + idAlvo + " removed.");
    }

    public void remove(Sale saleAlvo) {
        /*
         * Metodo para exclusao de determinado produto
         *
         * @args:
         * prodAlvo: produto a ser removido
         */
        remove(saleAlvo.getIdVenda());
    }

    public void update(Sale saleAlvo)
    {
        /*
         * Metodo para atualizar cadastro
         *
         * @args:
         * prodAlvo: O produto a ser modificado
         */

        saleDAO.update(saleAlvo);
    }

    public Sale uthenticate(Integer id_sale)
    {
        /*
         * Metodo para verificar se ha o cadastro de determinada sale
         *
         * @args:
         * id_sale: o nome do sale procurado
         *
         * @returns:
         * null se nao econtrar cadastro
         * sale_alvo: o cadastro da sale caso encontrado
         */

        Sale sale_alvo;
        try
        {
            sale_alvo = saleDAO.get(id_sale);
            if (sale_alvo == null)
            {
                Log.show(Log.Type.INFO,"Authentication Failed", "Sale not found.");
                return sale_alvo;
            }
            Log.show(Log.Type.INFO, "Sale Has been found!");
            System.out.println("up: " + "\n Id: " + sale_alvo.getIdVenda());
            return sale_alvo;
        }
        catch (ExceptionInInitializerError error)
        {
            error.printStackTrace();
            return null;
        }
    }

    public List<Sale> getAll() {
        /*
         * Metodo para pegar lista com todos os cadastro de transportadora
         */

        return saleDAO.list();
    }

}
