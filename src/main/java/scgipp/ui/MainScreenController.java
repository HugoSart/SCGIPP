package scgipp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainScreenController {

    @FXML Button btUsers;

    public void btUsersActionHandler(ActionEvent arg0) {
        try {
            openUsersStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUsersStage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/users_screen.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("SCGIPP");
        stage.setScene(new Scene(root));

        stage.show();

    }

}
