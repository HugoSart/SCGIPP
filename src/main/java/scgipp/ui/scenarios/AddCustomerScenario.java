package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scgipp.service.entities.Customer;
import scgipp.service.entities.User;
import scgipp.service.entities.embbeded.EmbeddableAddress;
import scgipp.service.entities.embbeded.EmbeddablePhone;
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
    public static final String FEEDBACK_NEW_COSTUMER = "new_customer";
    ObservableList<String> personTypes = FXCollections.observableArrayList("FISICA", "JURIDICA");

    @FXML private HBox menuBar;

    @FXML private Label lbSessionCustomer1;

    @FXML private Button btExit;

    @FXML private Button btOk;

    @FXML private Button btCancel;

    @FXML private TextField tfName;

    @FXML private Label lbNomeObrigatorio;

    @FXML private Label lbDocumentoObrigatorio;

    @FXML private ChoiceBox cbType;

    @FXML private TextField tfPhone;

    @FXML private TextField tfAddress;

    @FXML private Label lbFalseCpf;

    @FXML private DatePicker dpDate;

    @FXML private Label lbAlreadyOnSystem;

    @FXML private Label lCampoObrigatorio;

    @FXML private TextField tfCPF;

    @FXML private Label lbTelefoneObrigatorio;

    @FXML private Label lbEnderecoObrigatorio;

    @FXML private ChoiceBox<String> cbTipo;

    @FXML private void initialize()
    {
        cbTipo.setItems(personTypes);
    }


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

            String name = null,  address = null , phone = null, cpf = null, tipo = null;
            name = tfName.getText();
            address = tfAddress.getText();
            phone = tfPhone.getText();
            cpf = tfCPF.getText();
            tipo = cbTipo.getValue();
            LocalDate date = dpDate.getValue();

            EmbeddableAddress newAddress = new EmbeddableAddress();
            newAddress.setStreet(address);

            //pegando campos nulos
            boolean AlreadyOnSystem = false;
            
            for (Customer customer : customerManager.getAll()) {
                if (customer.getCpf_cnpj().equals(cpf)){
                    AlreadyOnSystem = true;
                    break;
                }
            }

            boolean FalseDocument = DocumentValidator.isValidCPF(cpf);

            lbNomeObrigatorio.setVisible(name.isEmpty());
            lbDocumentoObrigatorio.setVisible(cpf.isEmpty());
            lbTelefoneObrigatorio.setVisible(phone.isEmpty());
            lbEnderecoObrigatorio.setVisible(address.isEmpty());
            lbAlreadyOnSystem.setVisible(AlreadyOnSystem);
            lbFalseCpf.setVisible(!FalseDocument);

            if (FalseDocument && !AlreadyOnSystem ) {

                EmbeddablePhone newPhone = new EmbeddablePhone();
                newPhone.setFullPhone(phone);
                Customer newCustomer = new Customer(Person.Type.LEGAL, name, cpf, date);
                newCustomer.addAdress(newAddress);
                newCustomer.addPhone(newPhone);

                putFeedback(FEEDBACK_NEW_COSTUMER, newCustomer);
                processFeedbackAndFinish();
            }

        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);

    }
}
