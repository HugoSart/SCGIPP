<<<<<<< HEAD
package manager_test_cases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.service.entities.StockProduct;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.StockProductManager;

public class StockProductManagerTestCases {

    static int count = 0;

    @Before
    public void setUp(){
        DBConnection.initialize();
        Product productForStock = new Product("productForStock0" + ++count, "description0");
        ProductManager.addProduct(productForStock);
        DBConnection.manager().add(new StockProduct(productForStock, 0, 0.00));
    }

    @Test
    public void addStockProductTest(){

        Product productForStock1 = new Product("productForStock1", "description1");
        Product productForStock2 = new Product("productForStock2", "description2");
        ProductManager.addProduct(productForStock1);
        ProductManager.addProduct(productForStock2);
        StockProduct stockProductTest1 = new StockProduct(productForStock1, 4, 99.00);
        StockProduct stockProductTest2 = new StockProduct(productForStock2, 57, 107.99);

        Integer id1 = StockProductManager.addStockProduct(stockProductTest1);
        Integer id2 = StockProductManager.addStockProduct(stockProductTest2);

        Assert.assertEquals((int)id1, (int)stockProductTest1.getId());
        Assert.assertEquals((int)id2, (int)stockProductTest2.getId());
    }

    @Test
    public void updateProductTest(){

        Product productForStock3 = new Product("productForStock3", "description3");
        ProductManager.addProduct(productForStock3);
        Integer auxId = 1;

        StockProduct stockProduct3 = DBConnection.manager().get(StockProduct.class, auxId);
        stockProduct3.setProduct(productForStock3);
        stockProduct3.setQuantity(666);
        stockProduct3.setPrice(666.55);

        StockProductManager.updateStockProduct(stockProduct3);
        Assert.assertEquals(stockProduct3.getProduct(), productForStock3);
        Assert.assertEquals((int)stockProduct3.getQuantity(), 666);
        Assert.assertEquals(666.55, stockProduct3.getPrice(), 0);
    }

    @Test
    public void removeProductTest(){

        Product productForStock4 = new Product("productForStock4", "description4");
        ProductManager.addProduct(productForStock4);
        StockProduct stockProduct4 = new StockProduct(productForStock4, 45, 77.66);
        StockProductManager.removeStockProduct(stockProduct4);
        StockProductManager.updateStockProduct(stockProduct4);
    }

=======
package manager_test_cases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.Product;
import scgipp.service.entities.StockProduct;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.StockProductManager;

public class StockProductManagerTestCases {

    static int count = 0;

    @Before
    public void setUp(){
        DBConnection.initialize();
        Product productForStock = new Product("productForStock0" + ++count, "description0");
        ProductManager.addProduct(productForStock);
        DBConnection.manager().add(new StockProduct(productForStock, 0, 0.00));
    }

    @Test
    public void addStockProductTest(){

        Product productForStock1 = new Product("productForStock1", "description1");
        Product productForStock2 = new Product("productForStock2", "description2");
        ProductManager.addProduct(productForStock1);
        ProductManager.addProduct(productForStock2);
        StockProduct stockProductTest1 = new StockProduct(productForStock1, 4, 99.00);
        StockProduct stockProductTest2 = new StockProduct(productForStock2, 57, 107.99);

        Integer id1 = StockProductManager.addStockProduct(stockProductTest1);
        Integer id2 = StockProductManager.addStockProduct(stockProductTest2);

        Assert.assertEquals((int)id1, (int)stockProductTest1.getId());
        Assert.assertEquals((int)id2, (int)stockProductTest2.getId());
    }

    @Test
    public void updateProductTest(){

        Product productForStock3 = new Product("productForStock3", "description3");
        ProductManager.addProduct(productForStock3);
        Integer auxId = 1;

        StockProduct stockProduct3 = DBConnection.manager().get(StockProduct.class, auxId);
        stockProduct3.setProduct(productForStock3);
        stockProduct3.setQuantity(666);
        stockProduct3.setPrice(666.55);

        StockProductManager.updateStockProduct(stockProduct3);
        Assert.assertEquals(stockProduct3.getProduct(), productForStock3);
        Assert.assertEquals((int)stockProduct3.getQuantity(), 666);
        Assert.assertEquals(666.55, stockProduct3.getPrice(), 0);
    }

    @Test
    public void removeProductTest(){

        Product productForStock4 = new Product("productForStock4", "description4");
        ProductManager.addProduct(productForStock4);
        StockProduct stockProduct4 = new StockProduct(productForStock4, 45, 77.66);
        StockProductManager.removeStockProduct(stockProduct4);
        StockProductManager.updateStockProduct(stockProduct4);
    }

>>>>>>> [C]ObservableCustomer
}