import crud.CrudTestSuit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import scgipp.data.hibernate.DBConnection;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:56<br/>
 */
public class TestRunner {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(CrudTestSuit.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());

    }

}
