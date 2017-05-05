package person_management.client_management;

import person_management.Person;

import java.util.Calendar;

public class Client extends Person {

    public Client(int id, Type type, String name, String address, String zip) {
        super(id, type);
        setName(name);
        setAddress(address);
        setZip(zip);
    }

    public Client(int id, Type type, String name, String address, String zip, String cpf, Calendar birthDate) {
        this(id, type, name, address, zip);
        setCpf(cpf);
        setBirthDate(birthDate);
    }

}
