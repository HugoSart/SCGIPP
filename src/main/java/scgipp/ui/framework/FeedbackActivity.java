package scgipp.ui.framework;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:17<br/>
 */
public abstract class FeedbackActivity extends Activity {

    public FeedbackActivity(String fxmlPath) {
        super(fxmlPath);
    }

    // ============== ESPECIAL CALL METHODS =================== //

    final void feedback() {
        parent.onFeedback(id);
    }

    public void returnAndFinish() {
        feedback();
        finish();
    }

}
