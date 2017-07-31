package scgipp.service.transportadora_management;

import scgipp.data.hibernate.dao.TransportadoraDAO;
import scgipp.service.Address;
import scgipp.system.log.Log;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kira on 05/06/17.
 */

public class TransportadoraManager {

    /*
     * Classe que mantem interface com TransportadoraDAO
     *
     * @atributos:
     * transpDAO: instancia um objeto TransportadoraDAO
     */

    TransportadoraDAO transpDAO = new TransportadoraDAO();

    public TransportadoraManager(){
        /*
         * Metodo construtor da classe
         */
    }

    public Transportadora register(String c, String n, LocalDate d,
                                   String p, Address a) {
        /*
         * Metodo para o cadastro de nova transportadora
         *
         * @args:
         * t: type, c: cnpj, d: date, n: nome, p: numeros de telefone, a: email
         *
         * @returns:
         * novaTransp: a transportadora cadastrada
         */

        Transportadora novaTransp = new Transportadora(c, n, d, p, a);
        try {
            transpDAO.add(novaTransp);
        }
        catch (ExceptionInInitializerError error) {
            error.printStackTrace();
            Log.show(Log.Type.INFO, "Transportadora \"" + n + "\" registred fail.");
        }
        Log.show(Log.Type.INFO, "Transportadora \"" + n + "\" successufly registred.");

        return novaTransp;
    }

    public void remove(Integer idAlvo) {
        /*
         * Metodo para exclusao de cadastro com base no id
         *
         * @args:
         * idAlvo: e o id da transportadora a ser excluida
         */
        try {
            transpDAO.remove(idAlvo);
        } catch (ExceptionInInitializerError error) {
            error.printStackTrace();
        }
        Log.show(Log.Type.INFO, "Tranportadora ID: " + idAlvo + " removed.");
    }

    public void remove(Transportadora transpAlvo) {
        /*
         * Metodo para exclusao de determinada transportadora
         *
         * @args:
         * transpAlvo: a transportadora a ser removida
         */
        remove(transpAlvo.getId());
    }

    public void remove(String transpAlvo){
        /*
         * Metodo para excluasao de cadastro com base no nome da transportadora
         *
         * @args:
         * transpAlvo: Nome da transportadora a ser removida
         */

        remove(transpAlvo);
    }

    public void update(Transportadora transpAlvo) {
        /*
         * Metodo para atualizar cadastro
         *
         * @args:
         * transpAlvo: A transportadora a ser modificada
         */
        transpDAO.update(transpAlvo);
    }

    public Transportadora uthenticate(String transpNome) {
        /*
         * Metodo para verificar se ha o cadastro de determinada tranportadora
         *
         * @args:
         * transpNome: o nome da transportadora procurada
         *
         * @returns:
         * null se nao econtrar cadastro
         * transp: o cadastro da transportadora caso encontrada
         */
        Transportadora transp;

        try {
            transp = transpDAO.get(transpNome);
            if (transp == null) {
                Log.show(Log.Type.INFO,"Authentication Failed", "Transportadora not found.");
                return transp;
            }
            Log.show(Log.Type.INFO, "Authentication Successfuly Done!");
            System.out.println("up: " + transp.getName() + "\n Id: " + transp.getId());
            return transp;
        }
        catch (ExceptionInInitializerError error) {
            error.printStackTrace();
            return null;
        }
    }

    public List<Transportadora> getAll() {
        /*
         * Metodo para pegar lista com todos os cadastro de transportadora
         */

        return transpDAO.list();
    }
}
