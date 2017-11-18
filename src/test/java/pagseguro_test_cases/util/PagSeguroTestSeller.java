package pagseguro_test_cases.util;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 18:57<br/>
 */
public class PagSeguroTestSeller {

    public static final String email = "scgipp@gmail.com";
    public static final String productionToken = "05266A48764E42FF954A4816912CFD32";
    public static final String sandboxToken = "E6F09AA7BD24429288C9723009687660";

    public static AccountCredentials getCredentials() {
        try {
            return new AccountCredentials(email, productionToken, sandboxToken);
        } catch (PagSeguroServiceException e) {
            e.printStackTrace();
            return null;
        }
    }

}
