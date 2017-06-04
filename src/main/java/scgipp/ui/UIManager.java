package scgipp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by hugo_ on 03/06/2017.
 */
public abstract class UIManager {

    protected String TITLE;
    protected final URL location;

    public UIManager(String path) {
        this.location = getClass().getResource(path);
    }

    public Parent loadOnPane(Pane pane) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent root = null;

        try {

            root = loader.load();

            initializeData(loader);

            pane.getChildren().clear();
            pane.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;

    }

    public Stage newStage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Stage stage = null;

        try {

            Parent root = loader.load();

            initializeData(loader);

            stage = new Stage();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;

    }

    protected abstract void initializeData(FXMLLoader loader);

}
