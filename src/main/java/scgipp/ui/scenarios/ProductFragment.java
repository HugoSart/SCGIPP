<<<<<<< HEAD
=======
<<<<<<< HEAD
package scgipp.ui.scenarios;

public class ProductFragment {
}
=======
>>>>>>> [C]ObservableCustomer
package scgipp.ui.scenarios;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.Product;
import scgipp.service.managers.ProductManager;
import scgipp.ui.FXScenario.Fragment;



import java.util.List;

public class ProductFragment extends Fragment {

    private ProductManager productManager = ProductManager.getInstance();

    @FXML private AnchorPane productInfoPane;

    @FXML private TextField tfSearch;
    @FXML private Button btTest;
    @FXML private Button btAddProduct;
    @FXML private Button btRemove;

    public ProductFragment() {
        super("fxml/fragment_product.fxml");
    }

    @Override
    protected void onCreateView() {

        List<Product> productList = productManager.listAll();
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
<<<<<<< HEAD
=======
>>>>>>> 76e25ad21599ebbde2d82b2e7d8e16ccf6c4fcfb
>>>>>>> [C]ObservableCustomer
