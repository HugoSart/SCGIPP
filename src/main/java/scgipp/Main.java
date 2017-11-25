package scgipp;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.*;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.SaleManager;
import scgipp.ui.scenarios.LoginScenario;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 21:15<br/>
 */
public class Main extends Application {

    public static final String email = "scgipp@gmail.com";
    public static final String productionToken = "05266A48764E42FF954A4816912CFD32";
    public static final String sandboxToken = "E6F09AA7BD24429288C9723009687660";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PagSeguroConfig.setSandboxEnvironment();
        DBConnection.initialize();
        initTestUsers();

        Scenario loginScenario = new LoginScenario();
        Spawner.startScenario(loginScenario, null);

    }

    private static void initTestUsers() {
        DBManager dbManager = DBConnection.manager();
        dbManager.add(new Customer(Person.Type.LEGAL, "customer1", "0000000", LocalDate.now()));
        Address endereco = new Address();
        endereco.setStreet("colombo");
        dbManager.add(new User("admin", "admin", Permissions.UserType.ADM));
        dbManager.add(new User("hugovs", "hugovs", Permissions.UserType.ADM));
        dbManager.add(new User("amiguinho", "inteligega"));
        dbManager.add(new User("tskira", "tskira", Permissions.UserType.ADM));
        dbManager.add(new User("adario", "adario",Permissions.UserType.ADM));
        ProductManager.addProduct(new Product("notebook bom", "mto bom", 10, new BigDecimal(5)));

        /* Teste Venda */
        Product product1 = new Product("notebook bom po", "mto bom", 10, new BigDecimal(12));
        Product product2 = new Product("notebook louco", "mto mto bom", 10, new BigDecimal(10));
        Product product3 = new Product("notebook muito bom", "mto bom msmo", 10, new BigDecimal(20));
        List<Product> productList = new ArrayList<Product>();
        dbManager.add(product1);
        dbManager.add(product2);
        dbManager.add(product3);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        SaleBudget saleBudget1 = new SaleBudget(productList, endereco);
        //Sale newSale = new Sale(new User("comprador1", "comprador1", Permissions.UserType.ADM), new Customer(Person.Type.LEGAL, "customer2", "021312300", LocalDate.now()), "123123" );
        SaleManager sm = new SaleManager();
        //  sm.addSale(newSale);
    }

    public static AccountCredentials getCredentials() {
        try {
            return new AccountCredentials(email, productionToken, sandboxToken);
        } catch (PagSeguroServiceException e) {
            e.printStackTrace();
            return null;
        }
    }

}
