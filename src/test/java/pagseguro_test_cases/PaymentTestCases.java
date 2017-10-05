package pagseguro_test_cases;

import br.com.uol.pagseguro.domain.*;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.PaymentMode;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.PaymentService;
import br.com.uol.pagseguro.service.TransactionSearchService;
import org.junit.Before;
import org.junit.Test;
import pagseguro_test_cases.util.PagSeguroTestSeller;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 18:16<br/>
 */
public class PaymentTestCases {

    @Before
    public void setUp() {
        PagSeguroConfig.setSandboxEnvironment();
    }

    @Test
    public void generatePaymentTest() {

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCurrency(Currency.BRL);

        List<Item> items = new LinkedList<>();

        items.add(new Item("0001", "Notebook 5", 1,
                new BigDecimal("2435.00"), null, null)
        );
        items.add(new Item("0002", "Notebook 6", 1,
                new BigDecimal("2435.00"), 10000L,
                new BigDecimal("12.34")));

        paymentRequest.setItems(items);

        Shipping shipping = new Shipping();
        shipping.setAddress(new Address("BRA", "SP", "Sao Paulo",
                "Jardim Paulistano", "01452002",
                "Av. Brig. Faria Lima", "1384",
                "5o andar"));

        try {
            URL paymentURL = new URL(paymentRequest.register(new AccountCredentials(
                    PagSeguroTestSeller.email,
                    PagSeguroTestSeller.productionToken,
                    PagSeguroTestSeller.sandboxToken)));
            System.out.println("paymentURL: " + paymentURL);
        } catch (PagSeguroServiceException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkTransacionStatusTest() {

        try {
            Transaction transaction = TransactionSearchService.searchByCode(PagSeguroTestSeller.getCredentials(), "ADE3EAE9797E4D45A6BC1BAB72124E51");
            System.out.println("Transaction: " + transaction.getCode());
            System.out.println("               date   :   " + transaction.getDate());
            System.out.println("             status   :   " + transaction.getStatus());
            System.out.println("     paymant method   :   " + transaction.getPaymentMethod().getType().name());
            System.out.println("           shipping   :   " + transaction.getShipping().getType().name());
            System.out.println("             amount   :   R$" + transaction.getFeeAmount());
        } catch (PagSeguroServiceException e) {
            e.printStackTrace();
        }


        System.out.println();

        try {
            Transaction transaction = TransactionSearchService.searchByCode(PagSeguroTestSeller.getCredentials(), "DB7B10AA91884FB78CC65EACB197C2D6");
            System.out.println("Transaction: " + transaction.getCode());
            System.out.println("               date   :   " + transaction.getDate());
            System.out.println("             status   :   " + transaction.getStatus());
            System.out.println("     paymant method   :   " + transaction.getPaymentMethod().getType().name());
            System.out.println("           shipping   :   " + transaction.getShipping().getType().name());
            System.out.println("             amount   :   R$" + transaction.getFeeAmount());
        } catch (PagSeguroServiceException e) {
            e.printStackTrace();
        }

    }

}
