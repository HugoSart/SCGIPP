package scgipp.ui.scenarios;
//zé
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.Devolution;
import scgipp.service.entities.Product;
import scgipp.service.entities.Sale;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.managers.DevolutionManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.SaleManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddDevolutionScenario extends FeedbackScenario {

    private DevolutionManager devolutionManager = DevolutionManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance();
    private SaleManager saleManager = SaleManager.getInstance();

    public static final String FEEDBACK_NEW_DEVOLUTION = "new_devolution";

    @FXML private HBox menuBar;
    @FXML private Label lbSessionDevolution;
    @FXML private Button btExit;
    @FXML private Button btOk;
    @FXML private Button btCancel;
    @FXML private TextField tfSaleId;
    @FXML private TextField tfProductId;
    @FXML private TextField tfQuantity;
    @FXML private DatePicker dpDevolutionDay;
    @FXML private Label lbAlreadyRegistered;

    public AddDevolutionScenario() {
        super("fxml/scenario_add_devolution.fxml");
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

            Product newProduct = null;
            Sale newSale = null;

            Integer saleId = Integer.parseInt(tfSaleId.getText());
            Integer productId = Integer.parseInt(tfProductId.getText());
            String productName = null;
            for(Product p: productManager.listAll()){
                if(p.getId() == productId) {
                    newProduct = p;
                    break;
                }
            }
            for(Sale s: saleManager.getAll()){
                if(s.getId() == saleId){
                    newSale = s;
                    break;
                }
            }

            String devolutionDay = dpDevolutionDay.toString();
            Boolean isRestored = false;
            if(Integer.parseInt(tfQuantity.getText()) > 0){
                isRestored = true;
            }
            Integer quantity = Integer.parseInt(tfQuantity.getText());

            boolean productAlreadyRegistered = false;
            boolean saleAlreadyRegistered = false;

            for(Devolution d: devolutionManager.getAll()){
                if(d.getProduct().getId().equals(productId))
                    productAlreadyRegistered = true;
                if(d.getSale().getId().equals(saleId))
                    saleAlreadyRegistered = true;
            }

            lbAlreadyRegistered.setVisible(productAlreadyRegistered && saleAlreadyRegistered);

            if ((!productAlreadyRegistered) && (productId != null) && (!saleAlreadyRegistered) && (saleId != null) && (quantity > 0)) {

                Devolution devolution = new Devolution(newSale, newProduct, devolutionDay, isRestored, quantity);
                putFeedback(FEEDBACK_NEW_DEVOLUTION, devolution);
                processFeedbackAndFinish();
            }
        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }
}
