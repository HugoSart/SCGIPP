import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import other_tests.MailServerTest;
import test_cases.UserManagerTestCases;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 00:51<br/>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserManagerTestCases.class,
})
public class LocalTestSuite {
}
