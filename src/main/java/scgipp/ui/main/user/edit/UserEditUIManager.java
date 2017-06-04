package scgipp.ui.main.user.edit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.user_management.User;

import java.io.IOException;

public class UserEditUIManager {

    public static final String WINDOW_TITLE = "Usuário";

    public Stage newWindow(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/users_edit.fxml"));

        Stage stage = null;

        try {

            Parent root = loader.load();

            UserEditUIController controller = loader.getController();
            controller.initData(user);

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
