package scgipp.service;

import scgipp.data.hibernate.DBConnection;

/**
 * User: hugo_<br/>
 * Date: 08/10/2017<br/>
 * Time: 21:12<br/>
 */
public class SystemUtil {

    public static void exit() {
        DBConnection.finish();
        System.exit(0);
    }

}
