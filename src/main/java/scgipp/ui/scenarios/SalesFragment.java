package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Transaction;
import scgipp.ui.FXScenario.Fragment;

/**
 * User: hugo_<br/>
 * Date: 06/11/2017<br/>
 * Time: 22:08<br/>
 */
public class SalesFragment extends Fragment {

    public SalesFragment() {
        super("fxml/fragment_sales.fxml");
}

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setUpPagSeguroTab();
        setUpLocalTab();
    }

    private void setUpPagSeguroTab() {

    }

    private void setUpLocalTab() {

    }

}
