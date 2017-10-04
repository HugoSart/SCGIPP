package other_tests;

import org.junit.Test;
import scgipp.data.webservice.MailServer;

import java.util.Properties;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 22:36<br/>
 */
public class MailServerTest {

    @Test
    public void calcFreightSimulationTest() {

        MailServer mailServer = new MailServer();
        Properties properties = mailServer.calcFreightSimulation(null, null,
                MailServer.ServiceCode.SEDEX_VAREJO,
                "87200727", "87020260",
                "0.300", MailServer.PackageType.BOX_PACKAGE,
                40, 2, 30, 2,
                false, 250, false);

        System.out.println(properties.getProperty(MailServer.PropertyTags.VALUE));

    }


}
