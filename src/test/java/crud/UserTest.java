package crud;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.user_management.User;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:41<br/>
 */
public class UserTest {

    private User testUser1 = new User("test1", "test1"),
            testUser2 = new User("test2", "test2"),
            testUser3 = new User("test3", "test3"),
            duplicateUser1 = new User("dupTest1", "dupTest1"),
            duplicateUser2 = new User("dupTest1", "dupTest2");

    @Before
    public void setUp() {
        DBConnection.initialize();
    }

    @Test
    public void addUserTest() {
        DBConnection.manager().add(testUser1);
        DBConnection.manager().add(testUser2);
        DBConnection.manager().add(testUser3);
        DBConnection.manager().add(duplicateUser1);
        DBConnection.manager().add(duplicateUser2);
    }

    @Test
    public void updateUserTest() {
        addUserTest();
        testUser1.setPassword("updateTestWorks");
        DBConnection.manager().update(testUser1);
    }

    @After
    public void tearDown() {
        DBConnection.finish();
    }



}
