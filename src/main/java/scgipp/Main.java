package scgipp;

import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.ui.scenarios.LoginScenario;
import scgipp.ui.FXScenario.Scenario;
import scgipp.ui.FXScenario.Spawner;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 21:15<br/>
 */
public class    Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DBConnection.initialize();
        initTestUsers();

        Scenario loginScenario = new LoginScenario();
        Spawner.startScenario(loginScenario, null);

    }

    private static void initTestUsers() {
        DBManager dbManager = DBConnection.manager();
        dbManager.add(new User("admin", "admin", Permissions.UserType.ADM));
        dbManager.add(new User("hugovs", "hugovs", Permissions.UserType.ADM));
        dbManager.add(new User("inteligega", "inteligega"));
        dbManager.add(new User("tskira", "tskira", Permissions.UserType.ADM));
        dbManager.add(new User("adario", "adario",Permissions.UserType.ADM));
    }

}
