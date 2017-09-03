package framework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import scgipp.ui.framework.Activity;
import scgipp.ui.framework.FeedbackActivity;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 18:19<br/>
 */
public class EditUserActivity extends FeedbackActivity {

    @FXML
    TextField tfName;

    public EditUserActivity() {
        super("add_user.fxml");
    }

    @Override
    public void onConfigScene(Scene scene) {
        super.onConfigScene(scene);
        tfName.setText((String)getExtra("name"));
    }

    @FXML
    public void btOkActionHandler(ActionEvent actionEvent) {
        putExtra("return_newName", tfName.getText());
        returnAndFinish();
    }

    @FXML
    public void btCancelActionHandler(ActionEvent actionEvent) {
        finish();
    }

}
