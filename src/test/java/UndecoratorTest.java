import insidefx.undecorator.Undecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        Spawner.newWindow(stage, loginScenario, null);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
