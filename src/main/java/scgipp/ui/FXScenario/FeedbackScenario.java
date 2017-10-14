package scgipp.ui.FXScenario;

import java.util.HashMap;
import java.util.Map;

/**
 * User: hugo_<br/>
 * Date: 03/09/2017<br/>
 * Time: 17:17<br/>
 */
public abstract class FeedbackScenario extends Scenario {

    FeedbackListener listener;

    int requestCode;
    private int resultCode;

    private Map<String, Object> feedbackData = new HashMap<>();

    public interface FeedbackListener {
        void onFeedback(int requestCode, int resultCode, Map data);
    }

    public FeedbackScenario(String fxmlPath) {
        super(fxmlPath);
    }

    final void feedback() {
        listener.onFeedback(requestCode, resultCode, feedbackData);
    }

    protected void processFeedback() {
        feedback();
    }

    protected void processFeedbackAndFinish() {
        processFeedback();
        finish();
    }

    protected void putFeedback(String id, Object object) {
        feedbackData.put(id, object);
    }

    protected void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

}
