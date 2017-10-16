package manager_test_cases;

import javassist.NotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.service.entities.User;
import scgipp.service.managers.UserManager;

/**
 * User: hugo_<br/>
 * Date: 23/08/2017<br/>
 * Time: 22:41<br/>
 */
public class UserManagerTestCases {

    @Before
    public void setUp() {
        DBConnection.initialize();
        DBConnection.manager().add(new User("staticUser1", "staticUser1"));
        DBConnection.manager().add(new User("staticUser2", "staticUser2"));
    }

    @Test
    public void addUserTest() {

        User testUser1 = new User("testUser1", "testUser1");
        User testUser2 = new User("testUser2", "testUser2");
        User duplicateUser1 = new User("testUser1", "testUser1");

        Integer id1 = UserManager.getInstance().addUser(testUser1);
        Integer id2 = UserManager.getInstance().addUser(testUser2);
        Integer duplicateId1 = UserManager.getInstance().addUser(duplicateUser1);

        Assert.assertEquals((int)id1, (int)testUser1.getId());
        Assert.assertEquals((int)id2, (int)testUser2.getId());
        Assert.assertEquals(duplicateId1, null);

    }

    @Test
    public void updateUserTest() {

        Integer auxId = 1;

        User user = DBConnection.manager().get(User.class, auxId);
        user.setLogin("changedLogin");

        UserManager.getInstance().updateUser(user);
        user = DBConnection.manager().get(User.class, user.getId());
        Assert.assertEquals(user.getLogin(), "changedLogin");

    }

    @Test
    public void authenticateTest() {
        User staticUser1 = null;
        try {
            staticUser1 = UserManager.getInstance().authenticate("staticUser1", "staticUser1");
            Assert.assertEquals(staticUser1.getLogin(), "staticUser1");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        //DBConnection.exit();
    }

}
