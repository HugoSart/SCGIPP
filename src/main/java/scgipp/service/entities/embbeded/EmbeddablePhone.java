package scgipp.service.entities.embbeded;

import br.com.uol.pagseguro.domain.Phone;

import javax.persistence.Embeddable;

/**
 * User: hugo_<br/>
 * Date: 16/10/2017<br/>
 * Time: 18:35<br/>
 */

@Embeddable
public class EmbeddablePhone extends Phone {

    private String areaCode;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "[areaCode=" + getAreaCode() +
                ", number=" + getNumber();
    }

}
