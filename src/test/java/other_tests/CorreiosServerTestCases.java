package other_tests;

import org.junit.Test;
import scgipp.data.webservice.CorreiosServer;

import java.util.Properties;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 22:36<br/>
 */
public class CorreiosServerTestCases {

    @Test
    public void calcFreightSimulationTest() {

        CorreiosServer correiosServer = new CorreiosServer();
        Properties properties = correiosServer.calcFreightSimulation(null, null,
                CorreiosServer.ServiceCode.SEDEX_VAREJO,
                "87200727", "87020260",
                "0.300", CorreiosServer.PackageType.BOX_PACKAGE,
                40, 2, 30, 2,
                false, 250, false);

        System.out.println(properties.getProperty(CorreiosServer.PropertyTags.VALUE));

    }


}
