package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.Customer;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.Permissions;
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.CustomerManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCustomerScenario extends FeedbackScenario{

    private CustomerManager customerManager = CustomerManager.getInstance();

    @FXML private HBox menuBar;
    @FXML private Label lbSessionCustomer1;
    @FXML private Button btExit;
    @FXML private Button btOk;
    @FXML private Button btCancel;
    @FXML private TextField tfName;
    @FXML private PasswordField tfCPF;
    @FXML private Label lbLoginAlreadyExists;
    @FXML private Label lbVoidfield;
    @FXML private ChoiceBox<Person.Type> cbType;
    @FXML private TextField tfPhone;
    @FXML private TextField tfAddress;
    @FXML private DatePicker dpDate;
    @FXML private Label lbFalseCpf;
    @FXML private Label lbAlreadyOnSystem;

    public AddCustomerScenario(){super("fxml/scenario_add_customer.fxml"); }

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

            String name = tfName.getText(), address = tfAddress.getText()  , phone = tfPhone.getText(), cpf;
            LocalDate date = dpDate.getValue();
            cpf = tfCPF.getText();

            boolean AlreadyOnSystem = false;
            for (Customer customer : customerManager.getAll()) {
                if (customer.getCpf_cnpj().equals(cpf) {
                    AlreadyOnSystem = true;
                    break;
                }
            }

            boolean FalseDocument = DocumentValidator.isValidCPF(cpf)

            lbAlreadyOnSystem.setVisible(AlreadyOnSystem);
            lbFalseCpf.setVisible(!FalseDocument));

            if (FalseDocument && !AlreadyOnSystem)) {

                User user = new User(login, password);

                for (CheckBox checkBox : lvPermissions.getItems()) {
                    if (checkBox.isSelected()) {
                        user.permissions.add(Permissions.Permission.valueOf(checkBox.getText()));
                    }
                }

                putFeedback(FEEDBACK_NEW_USER, user);
                processFeedbackAndFinish();
            }


        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);

        List<CheckBox> permissionsCheckBoxList = new ArrayList<>();
        for (Permissions.Permission permission : Permissions.Permission.values()) {
            CheckBox cb = new CheckBox();
            cb.setText(permission.toString());
            permissionsCheckBoxList.add(cb);
        }
        lvPermissions.setItems(FXCollections.observableArrayList(permissionsCheckBoxList));

    }
}
