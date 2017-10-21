package validators_test_cases;

import org.junit.Assert;
import org.junit.Test;
import scgipp.service.validators.DocumentValidator.DocumentValidator;

public class CPFValidatorTests {

    DocumentValidator documentValidator = new DocumentValidator();

    @Test
    public void validTest(){
        Assert.assertTrue(documentValidator.isValidCPF("82571385500"));
        Assert.assertTrue(documentValidator.isValidCPF("38574744409"));
        Assert.assertTrue(documentValidator.isValidCPF("79120685386"));
        Assert.assertTrue(documentValidator.isValidCPF("79120685386"));
        Assert.assertTrue(documentValidator.isValidCPF("81163316717"));
    }

    @Test
    public void invalidTest(){
        Assert.assertTrue(!documentValidator.isValidCPF("00000000000"));
        Assert.assertTrue(!documentValidator.isValidCPF("11111111111"));
        Assert.assertTrue(!documentValidator.isValidCPF("11223322113"));
        Assert.assertTrue(!documentValidator.isValidCPF("11223322111"));
        Assert.assertTrue(!documentValidator.isValidCPF("12223322111"));
    }
}
