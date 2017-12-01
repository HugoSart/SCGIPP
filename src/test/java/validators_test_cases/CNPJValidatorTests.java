package validators_test_cases;

import org.junit.Assert;
import org.junit.Test;
import scgipp.service.validators.DocumentValidator.DocumentValidator;

/**
 * @author : Anderson Dario
 */

public class CNPJValidatorTests {

    @Test
    public void validTest(){
        Assert.assertTrue(DocumentValidator.isValidCPNJ("44479557000122"));
        Assert.assertTrue(DocumentValidator.isValidCPNJ("24209266000107"));
        Assert.assertTrue(DocumentValidator.isValidCPNJ("48951201000109"));
        Assert.assertTrue(DocumentValidator.isValidCPNJ("26135640000100"));
    }

    @Test
    public void invalidTest(){
        Assert.assertTrue(!DocumentValidator.isValidCPNJ("00000000000000"));
        Assert.assertTrue(!DocumentValidator.isValidCPNJ("11111111111111"));
        Assert.assertTrue(!DocumentValidator.isValidCPNJ("11111111111122"));
        Assert.assertTrue(!DocumentValidator.isValidCPNJ("11116711111122"));
        Assert.assertTrue(!DocumentValidator.isValidCPNJ("19116711131122"));
    }
}
