package scgipp.ui.scenarios;

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

public class UpdateDevolutionScenario extends FeedbackScenario {

    private DevolutionManager devolutionManager = DevolutionManager.getInstance();
    private ProductManager productManager = ProductManager.getInstance();
    private SaleManager saleManager = SaleManager.getInstance();

    private Devolution updateThisDevolution;

    public static final String FEEDBACK_NEW_DEVOLUTION = "new_devolution";

    @FXML private HBox menuBar;
    @FXML private Label lbSessionDevolution;
    @FXML private Button btExit;
    @FXML private Button btOk;
    @FXML private Button btCancel;
    @FXML private TextField tfQuantity;
    @FXML private DatePicker dpDevolutionDay;
    @FXML private Label lbError;

    public UpdateDevolutionScenario() {
        super("fxml/scenario_update_devolution.fxml");
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

            String devolutionDay = dpDevolutionDay.toString();
            Boolean isRestored = Integer.parseInt(tfQuantity.getText()) <= 0;
            Integer quantity = Integer.parseInt(tfQuantity.getText());

            lbError.setVisible(!tfQuantity.getText().isEmpty() || quantity < 0);

            if (devolutionDay != null && quantity >= 0) {

                this.updateThisDevolution.setDevolutionDate(devolutionDay);
                this.updateThisDevolution.setRestoreToStock(isRestored);
                this.updateThisDevolution.setQuantity(quantity);

                putFeedback(FEEDBACK_NEW_DEVOLUTION, this.updateThisDevolution);
                processFeedbackAndFinish();
            }
        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }
}
