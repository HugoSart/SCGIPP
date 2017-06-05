package scgipp.service.product_management;

import scgipp.system.log.Log;

import java.util.List;

/**
 * Created by kira on 05/06/17.
 */
public class ProductManager {
    /*
     * Classe que mantem interface com ProductDAO
     *
     * @atributos:
     * productDAO: instancia um objeto ProductDAO
     */

    ProductDAO productDAO = new ProductDAO();

    public ProductManager(){
        /*
         * Metodo construtor da classe
         */
    }

    public Product register(int prodTipo, int prodUn, String prodNome,
                            String prodDesc, double prodValue) {
        /*
         * Metodo para o cadastro de novo produto
         *
         * @args:
         * prodTipo: tipo do produto
         * prodUn: unidades disponiveis
         * prodNome: Nome do produto
         * prodDesc: Descricao do produto
         * prodValue: valor de venda do produto
         *
         * @returns:
         * novoProduto: o produto cadastrado
         */

        Product novoProduto = new Product(prodTipo, prodUn, prodNome, prodDesc, prodValue);
        try {
            productDAO.add(novoProduto);
        }
        catch (ExceptionInInitializerError error) {
            error.printStackTrace();
            Log.show(Log.Type.INFO, "Product: \"" + prodNome + "\" registred fail.");
        }
        Log.show(Log.Type.INFO, "Product: \"" + prodNome + "\" successufly registred.");

        return novoProduto;
    }

    public void remove(Integer idAlvo) {
        /*
         * Metodo para exclusao de cadastro com base no id
         *
         * @args:
         * idAlvo: e o id do produto a ser excluido
         */
        try {
            productDAO.remove(idAlvo);
        } catch (ExceptionInInitializerError error) {
            error.printStackTrace();
        }
        Log.show(Log.Type.INFO, "Produto ID: " + idAlvo + " removed.");
    }

    public void remove(Product prodAlvo) {
        /*
         * Metodo para exclusao de determinado produto
         *
         * @args:
         * prodAlvo: produto a ser removido
         */
        remove(prodAlvo.getId());
    }

    public void remove(String prodAlvo){
        /*
         * Metodo para excluasao de cadastro com base no nome do produto
         *
         * @args:
         * prodAlvo: Nome do produto a ser removido
         */

        remove(prodAlvo);
    }

    public void update(Product prodAlvo) {
        /*
         * Metodo para atualizar cadastro
         *
         * @args:
         * prodAlvo: O produto a ser modificado
         */

        productDAO.update(prodAlvo);
    }

    public Product uthenticate(String prodNome) {
        /*
         * Metodo para verificar se ha o cadastro de determinado produto
         *
         * @args:
         * prodNome: o nome do produto procurado
         *
         * @returns:
         * null se nao econtrar cadastro
         * prod: o cadastro do produto caso encontrado
         */

        Product prod;
        try {
            prod = productDAO.get(prodNome);
            if (prod == null) {
                Log.show(Log.Type.INFO,"Authentication Failed", "Product not found.");
                return prod;
            }
            Log.show(Log.Type.INFO, "Product Has been found!");
            System.out.println("up: " + prod.getName() + "\n Id: " + prod.getId());
            return prod;
        }
        catch (ExceptionInInitializerError error) {
            error.printStackTrace();
            return null;
        }
    }

    public List<Product> getAll() {
        /*
         * Metodo para pegar lista com todos os cadastro de transportadora
         */

        return productDAO.list();
    }
}
