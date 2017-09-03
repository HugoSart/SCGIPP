package scgipp.ui.framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * User: hugo_<br/>
 * Date: 28/08/2017<br/>
 * Time: 20:59<br/>
 */
public class Spawner {

    public static void newWindow(@Nullable Stage stage, @NotNull Activity activity, @NotNull String id, @Nullable Activity parent) {

        activity.id = id;
        activity.parent = parent;

        if (activity.parent != null) activity.getParent().children.put(activity.getId(), activity);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(activity.getClass().getClassLoader().getResource(activity.fxmlPath));
            fxmlLoader.setController(activity);

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            activity.create(stage);
            stage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void newWindow(@Nullable Stage stage, @NotNull Activity activity, @Nullable Activity parent) {
        newWindow(stage, activity, activity.toString(), parent);
    }

    public static void newWindow(@NotNull Activity activity, @NotNull String id, @Nullable Activity parent) {
        newWindow(new Stage(), activity, id, parent);
    }

    public static void newWindow(@NotNull Activity activity, @Nullable Activity parent) {
        newWindow(activity, activity.toString(), parent);
    }

}
