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

    @Before
    public void setUp(){
        DBConnection.initialize();
        Product product = new Product("product", "description");
        ProductManager.addProduct(product);
        DBConnection.manager().add(new StockProduct(product, 0, 0.00));
    }

    @Test
    public void addStockProductTest(){

        Product product1 = new Product("product1", "description");
        Product product2 = new Product("product2", "description");
        ProductManager.addProduct(product1);
        ProductManager.addProduct(product2);
        StockProduct stockProductTest1 = new StockProduct(product1, 0, 0.00);
        StockProduct stockProductTest2 = new StockProduct(product2, 0, 0.00);

        Integer id1 = StockProductManager.addStockProduct(stockProductTest1);
        Integer id2 = StockProductManager.addStockProduct(stockProductTest2);

        Assert.assertEquals((int)id1, (int)stockProductTest1.getId());
        Assert.assertEquals((int)id2, (int)stockProductTest2.getId());
    }

    @Test
    public void updateProductTest(){

        Product product = new Product("newProduct", "newDescription");
        ProductManager.addProduct(product);
        Integer auxId = 1;

        StockProduct stockProduct = DBConnection.manager().get(StockProduct.class, auxId);
        stockProduct.setProduct(product);
        stockProduct.setQuantity(10);
        stockProduct.setPrice(10.10);

        StockProductManager.updateStockProduct(stockProduct);
        Assert.assertEquals(stockProduct.getProduct(), product);
        Assert.assertEquals((int)stockProduct.getQuantity(), 10);
        Assert.assertEquals(10.10, stockProduct.getPrice(), 0);
    }

    @Test
    public void removeProductTest(){

        Product product = new Product("product", "description");
        ProductManager.addProduct(product);
        StockProduct stockProduct = new StockProduct(product, 0, 0.00);
        StockProductManager.removeStockProduct(stockProduct);
        StockProductManager.updateStockProduct(stockProduct);
    }

}