package scgipp.ui.user.edit.permissions;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import scgipp.service.user_management.User;
import scgipp.ui.user.edit.EditUsersController;

import java.io.IOException;
import java.util.List;

public class EditPermissionsManager {

    public void loadOnPane(Pane pane, User user) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/users_edit_permissions.fxml"));

        try {

            Parent root = loader.load();

            EditPermissionsController controller = loader.getController();
            controller.initData(user);

            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
