package framework_test.activities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.ui.FXScenario.FeedbackScenario;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:30<br/>
 */
public class AddUserScenario extends FeedbackScenario {

    @FXML
    TextField tfName;

    public AddUserScenario() {
        super("add_user.fxml");
    }

    @Override
    public void onConfigStage(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @FXML
    public void btOkActionHandler(ActionEvent actionEvent) {
        putFeedback("userName", tfName.getText());
        processFeedbackAndFinish();
    }

    @FXML
    public void btCancelActionHandler(ActionEvent actionEvent) {
        finish();
    }

}
