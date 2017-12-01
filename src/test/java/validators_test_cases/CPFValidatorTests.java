package validators_test_cases;

import org.junit.Assert;
import org.junit.Test;
import scgipp.service.validators.DocumentValidator.DocumentValidator;

/**
 * @author : Anderson Dario
 */

public class CPFValidatorTests {

    @Test
    public void validTest(){
        Assert.assertTrue(DocumentValidator.isValidCPF("82571.3855.00"));
        Assert.assertTrue(DocumentValidator.isValidCPF("38574744409"));
        Assert.assertTrue(DocumentValidator.isValidCPF("791206853/86"));
        Assert.assertTrue(DocumentValidator.isValidCPF("79120685386"));
        Assert.assertTrue(DocumentValidator.isValidCPF("81163316717"));
    }

    @Test
    public void invalidTest(){
        Assert.assertTrue(!DocumentValidator.isValidCPF("0000/0000000"));
        Assert.assertTrue(!DocumentValidator.isValidCPF("11111111111"));
        Assert.assertTrue(!DocumentValidator.isValidCPF("11223.322113"));
        Assert.assertTrue(!DocumentValidator.isValidCPF("11223322111"));
        Assert.assertTrue(!DocumentValidator.isValidCPF("12223322111"));
    }
}
