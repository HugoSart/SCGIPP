package scgipp.ui.main.user.add;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserUIManager {

    public static final String WINDOW_TITLE = "Novo usuário";

    public Stage newWindow() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/users_add_screen.fxml"));

        Stage stage = null;

        try {

            Parent root = loader.load();

            stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(WINDOW_TITLE);
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;
    }

}
