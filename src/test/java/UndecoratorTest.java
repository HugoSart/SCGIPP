import javafx.application.Application;
import javafx.stage.Stage;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.scenarios.LoginScenario;

/**
 * User: hugo_<br/>
 * Date: 09/10/2017<br/>
 * Time: 18:35<br/>
 */
public class UndecoratorTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        LoginScenario loginScenario = new LoginScenario();
        Spawner.startScenario(loginScenario, null, stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
