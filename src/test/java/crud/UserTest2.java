package crud;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.user_management.User;

import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:41<br/>
 */
public class UserTest2 {

    private User testUser1 = new User("test1", "test1");

    @Before
    public void setUp() {
        DBConnection.initialize();
    }

    @Test
    public void addUserTest() {
        Integer id = DBConnection.manager().add(testUser1);
        Assert.assertEquals((int)id, (int)testUser1.getId());
    }

    @After
    public void tearDown() {
        DBConnection.finish();
    }

}
