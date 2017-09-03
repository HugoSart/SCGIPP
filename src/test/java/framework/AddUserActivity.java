package framework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.ui.framework.Activity;
import scgipp.ui.framework.FeedbackActivity;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:30<br/>
 */
public class AddUserActivity extends FeedbackActivity {

    @FXML
    TextField tfName;

    public AddUserActivity() {
        super("add_user.fxml");
    }

    @Override
    public void onConfigStage(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @FXML
    public void btOkActionHandler(ActionEvent actionEvent) {
        putExtra("return_user", tfName.getText());
        returnAndFinish();
    }

    @FXML
    public void btCancelActionHandler(ActionEvent actionEvent) {
        finish();
    }

}
