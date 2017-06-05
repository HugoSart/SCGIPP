package scgipp.service.transportadora_management;

import java.time.LocalDate;
import javax.persistence.*;
import scgipp.service.*;

/**
 * Created by kira on 05/06/17.
 */

@Entity
public class Transportadora extends Person {

    /*
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
     */

    public Transportadora(String c, String n, LocalDate d,
                          String p, Adress a){
        /*
         * Metodo construtor da classe
         *
         * @args:
         * t: type, c: cnpj, d: date, n: nome, p: numeros de telefone, a: email
         */

        super(Type.LEGAL, n, c, d);
        super.addAdress(a);
        super.addPhone(p);
    }

    public Transportadora(){}





}
