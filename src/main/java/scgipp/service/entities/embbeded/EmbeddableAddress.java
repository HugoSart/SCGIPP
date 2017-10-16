package scgipp.service.entities.embbeded;

import br.com.uol.pagseguro.domain.Address;

import javax.persistence.Embeddable;

/**
 * Created by hsart on 13/05/17.
 */
@Embeddable
public class EmbeddableAddress extends Address {

    private String postalCode;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "[postalCode="  + getPostalCode() +
                ", street="     + getStreet() +
                ", number="     + getNumber() +
                ", complement=" + getComplement() +
                ", district="   + getDistrict() +
                ", city="       + getCity() +
                ", stage="      + getState() +
                ", country="    + getCountry() + "]\n";
    }

}
