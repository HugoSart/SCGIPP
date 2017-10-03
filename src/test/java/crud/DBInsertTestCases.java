package crud;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.entity.User;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:41<br/>
 */
public class DBInsertTestCases {

    @Before
    public void setUp() {
        DBConnection.initialize();
    }

    @Test
    public void addUserTest() {

        User testUser1 = new User("testUser1", "testUser1");
        User testUser2 = new User("testUser2", "testUser2");
        User duplicateUser1 = new User("testUser1", "testUser1");

        Integer id1 = DBConnection.manager().add(testUser1);
        Integer id2 = DBConnection.manager().add(testUser2);
        Integer duplicateId1 = DBConnection.manager().add(duplicateUser1);

        Assert.assertEquals((int)id1, (int)testUser1.getId());
        Assert.assertEquals((int)id2, (int)testUser2.getId());
        Assert.assertEquals((int)duplicateId1, (int)duplicateUser1.getId());

    }


    @After
    public void tearDown() {
        DBConnection.finish();
    }

}
