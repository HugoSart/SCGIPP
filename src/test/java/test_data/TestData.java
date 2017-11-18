<<<<<<< HEAD
package test_data;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.User;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 13:57<br/>
 */
public class TestData {

    private static final DBManager dbManager = DBConnection.manager();

    public static void initTestUsers() {
        dbManager.add(new User("admin", "admin"));
        dbManager.add(new User("hugovs", "hugovs"));
        dbManager.add(new User("inteligega", "inteligega"));
        dbManager.add(new User("tskira", "tskira"));
        dbManager.add(new User("adario", "adario"));
    }

}
=======
package test_data;

import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.User;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 13:57<br/>
 */
public class TestData {

    private static final DBManager dbManager = DBConnection.manager();

    public static void initTestUsers() {
        dbManager.add(new User("admin", "admin"));
        dbManager.add(new User("hugovs", "hugovs"));
        dbManager.add(new User("inteligega", "inteligega"));
        dbManager.add(new User("tskira", "tskira"));
        dbManager.add(new User("adario", "adario"));
    }

}
>>>>>>> [C]ObservableCustomer
