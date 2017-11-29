package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.Product;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddProductScenario extends FeedbackScenario {

    private ProductManager productManager = ProductManager.getInstance();

    public static final String FEEDBACK_NEW_PRODUCT = "new_product";

    @FXML private HBox menuBar;
    @FXML private Label lbSessionProduct;
    @FXML private Button btExit;
    @FXML private Button btOk;
    @FXML private Button btCancel;
    @FXML private TextField tfName;
    @FXML private TextField tfQuantity;
    @FXML private TextField tfPrice;
    @FXML private TextField tfDescription;
    @FXML private Label lbProductAlreadyExists;
    @FXML private Label lbName;
    @FXML private Label lbQuantity;
    @FXML private Label lbPrice;

    public AddProductScenario() {
        super("fxml/scenario_add_product.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
    }

    @Override
    protected void onConfigStage(Stage stage) {

        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, null, null);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        btOk.setOnAction(event -> {

            String name = tfName.getText();
            String description = tfDescription.getText();
            Integer quantity = Integer.parseInt(tfQuantity.getText());
            BigDecimal price = new BigDecimal(Float.parseFloat(tfPrice.getText()));

            boolean productAlreadyRegistered = false;
            //lbName.setVisible(productAlreadyRegistered);
            //boolean invalidQuantity = (quantity == null) || !((quantity > 0) && (quantity < Integer.MAX_VALUE));
            //boolean invalidPrice = (price == null) || !((price.doubleValue() > 0) && (price.doubleValue() < Double.MAX_VALUE));

            for (Product product : productManager.listAll()) {
                if (product.getName().equals(name) || tfName.getText().isEmpty()) {
                    productAlreadyRegistered = true;
                    break;
                }
            }

            //lbQuantity.setVisible(invalidQuantity);
            //lbPrice.setVisible(invalidPrice);
            lbName.setVisible(productAlreadyRegistered);

            if ((!productAlreadyRegistered) && (name != null) && (price != null) && (price.doubleValue() >= 0) && (quantity != null) && (quantity >= 0)) {

                Product product = new Product(name, description, quantity, price);
                putFeedback(FEEDBACK_NEW_PRODUCT, product);
                processFeedbackAndFinish();
            }
        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }
}
