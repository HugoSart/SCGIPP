package manager_test_cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.service.managers.ProductManager;

import java.util.List;

public class ProductManagerTestCases {

    @Before
    public void setUp(){
        DBConnection.initialize();;
        DBConnection.manager().add(new Product("productName1", "productDescription1"));
    }

    @Test
    public void addProductTest(){

        Product productTest1 = new Product("productNameTest1", "productDescriptionTest1");
        Product productTest2 = new Product("productNameTest2", "productDescriptionTest2");
        Product duplicatedProduct1 = new Product("productNameTest1", "productDescriptionTest1");

        Integer id1 = ProductManager.addProduct(productTest1);
        Integer id2 = ProductManager.addProduct(productTest2);
        Integer duplicatedId1 = ProductManager.addProduct(duplicatedProduct1);

        Assert.assertEquals((int)id1, (int)productTest1.getId());
        Assert.assertEquals((int)id2, (int)productTest2.getId());
        Assert.assertEquals(duplicatedId1, null);
    }

    @Test
    public void updateProductTest(){

        Integer auxId = 1;

        Product product = DBConnection.manager().get(Product.class, auxId);
        product.setName("newName");
        product.setDescription("newDescription");

        ProductManager.updateProduct(product);
        product = DBConnection.manager().get(Product.class, product.getId());
        Assert.assertEquals(product.getName(), "newName");
        Assert.assertEquals(product.getDescription(), "newDescription");
    }

    @Test
    public void removeProductTest(){

        Product product = new Product("productName", "description");
        ProductManager.removeProduct(product);
        ProductManager.updateProduct(product);
    }

    @After
    public void tearDown(){
        //DBConnection.finish();
    }

}