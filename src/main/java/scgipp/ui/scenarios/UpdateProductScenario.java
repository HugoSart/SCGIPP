package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.Customer;
import scgipp.service.entities.Product;
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.CustomerManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.Chronology;

public class UpdateProductScenario extends FeedbackScenario {

    private ProductManager productManager = ProductManager.getInstance();
    private Product updateThisProduct;

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
    @FXML private TextField tfWeight;
    @FXML private Label lbWeight;
    @FXML private Label lbName;
    @FXML private Label lbQuantity;
    @FXML private Label lbPrice;

    public UpdateProductScenario(Product product2Update){
        super("fxml/scenario_update_product.fxml");
        this.updateThisProduct = product2Update;
    }

    @FXML private void initialize()
    {

        tfName.setText(this.updateThisProduct.getName());
        tfDescription.setText(this.updateThisProduct.getDescription());
        tfPrice.setText(updateThisProduct.getAmount().toString());
        tfQuantity.setText(updateThisProduct.getQuantity().toString());
        tfWeight.setText(updateThisProduct.getWeight().toString());
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

            Boolean productAlreadyExists = false;
            String oldName = this.updateThisProduct.getName();

            String name = tfName.getText();
            String description = tfDescription.getText();
            Integer quantity = Integer.parseInt(tfQuantity.getText());
            BigDecimal price = new BigDecimal(Float.parseFloat(tfPrice.getText()));
            Long weight = Long.parseLong(tfWeight.getText());

            for(Product p: productManager.listAll()){
                if(p.getName().equals(oldName)){
                }
                else if(p.getName().equals(name)) {
                    productAlreadyExists = true;
                    break;
                }
            }

            lbName.setVisible(productAlreadyExists);
            lbQuantity.setVisible(tfQuantity.getText().isEmpty() || Integer.parseInt(tfQuantity.getText()) < 0);
            lbPrice.setVisible(tfPrice.getText().isEmpty() || Double.parseDouble(tfPrice.getText()) < 0.0);
            lbWeight.setVisible(tfWeight.getText().isEmpty() || Double.parseDouble(tfWeight.getText()) < 0.0);

            if ((name != null) && !productAlreadyExists && (price.doubleValue() >= 0) && (quantity >= 0) && (quantity.doubleValue() >= 0)) {

                this.updateThisProduct.setName(name);
                this.updateThisProduct.setDescription(description);
                this.updateThisProduct.setAmount(price);
                this.updateThisProduct.setQuantity(quantity);
                this.updateThisProduct.setWeight(weight);

                putFeedback(FEEDBACK_NEW_PRODUCT, this.updateThisProduct);
                processFeedbackAndFinish();
            }
        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(Scenario.ScenarioStyle.BETTER_UNDECORATED);

    }

}