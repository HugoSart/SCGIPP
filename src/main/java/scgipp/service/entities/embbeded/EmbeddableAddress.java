package scgipp.service.entities.embbeded;

import br.com.uol.pagseguro.domain.Address;

import javax.persistence.Embeddable;

/**
 * Created by hsart on 13/05/17.
 */
@Embeddable
public class EmbeddableAddress extends Address {

    private String postalCode;

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
