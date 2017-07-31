package scgipp.service.transportadora_management;

import java.time.LocalDate;
import javax.persistence.*;
import scgipp.service.*;

/**
 * Created by kira on 05/06/17.
 */

/**
     * Classe para implementacao da transportadora
     *
     * Define a classe transportadora
     * Filho da classe Person
     * Utilizada para cadastrar as tranportadoras no sistema
     * Assim e um dado persistente (entidade)
     *
     * Atributos:
     * id: a chave primaria, codigo da transportadora, gerado
     * type: JURIDICO, padrao
     * cpf: no caso, cnpj
     * date: data de fundacao
     * name: nome fantasia
     * phones: numero de contato
     * address: endereco eletronico
 *
 */
@Entity
public class Transportadora extends Person {



    /**
         * Metodo construtor da classe
         *
         * @param c: type
     *     @param n cnpj, d: date, n: nome, p: numeros de telefone, a: email
     */
    public Transportadora(String c, String n, LocalDate d, String p, Address a){


        super(Type.LEGAL, n, c, d);
        super.addAdress(a);
        super.addPhone(p);

    }

    public Transportadora(){}


}
