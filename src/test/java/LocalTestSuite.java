import manager_test_cases.CustomerManegerTestCases;
import manager_test_cases.ProductManagerTestCases;
import manager_test_cases.StockProductManagerTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import manager_test_cases.UserManagerTestCases;
import scgipp.service.entities.StockProduct;
import scgipp.service.managers.CustomerManager;
import scgipp.service.managers.StockProductManager;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 00:51<br/>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserManagerTestCases.class,
        ProductManagerTestCases.class,
        StockProductManagerTestCases.class,
        CustomerManegerTestCases.class
})
public class LocalTestSuite {
}
