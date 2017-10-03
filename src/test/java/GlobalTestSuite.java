import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test_cases.UserManagerTestCases;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:54<br/>
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserManagerTestCases.class,
})
public class GlobalTestSuite {

}
