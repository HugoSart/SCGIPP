package validators_test_cases;

import org.junit.Assert;
import org.junit.Test;
import scgipp.service.validators.DocumentValidator.DocumentValidator;

public class CNPJValidatorTests {

    DocumentValidator documentValidator = new DocumentValidator();

    @Test
    public void validTest(){
        Assert.assertTrue(documentValidator.isValidCPNJ("44479557000122"));
        Assert.assertTrue(documentValidator.isValidCPNJ("24209266000107"));
        Assert.assertTrue(documentValidator.isValidCPNJ("48951201000109"));
        Assert.assertTrue(documentValidator.isValidCPNJ("26135640000100"));
    }

    @Test
    public void invalidTest(){
        Assert.assertTrue(!documentValidator.isValidCPNJ("00000000000000"));
        Assert.assertTrue(!documentValidator.isValidCPNJ("11111111111111"));
        Assert.assertTrue(!documentValidator.isValidCPNJ("11111111111122"));
        Assert.assertTrue(!documentValidator.isValidCPNJ("11116711111122"));
        Assert.assertTrue(!documentValidator.isValidCPNJ("19116711131122"));
    }
}
