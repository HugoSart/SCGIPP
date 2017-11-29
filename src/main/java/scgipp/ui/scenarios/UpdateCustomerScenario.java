package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
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
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.CustomerManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.FXScenario.Scenario;

import java.time.LocalDate;
import java.time.chrono.Chronology;

public class UpdateCustomerScenario extends FeedbackScenario {

    private CustomerManager customerManager = CustomerManager.getInstance();

    public static final String FEEDBACK_NEW_CUSTOMER = "new_customer";

    ObservableList<String> personTypes = FXCollections.observableArrayList("FISICA", "JURIDICA");

    private Customer updateThisCustomer;
    @FXML
    private HBox menuBar;

    @FXML
    private Label lbSessionCustomer1;

    @FXML
    private Button btExit;

    @FXML
    private Button btOk;

    @FXML
    private Button btCancel;

    @FXML
    private TextField tfName;

    @FXML
    private Label lbNomeObrigatorio;

    @FXML
    private Label lbDocumentoObrigatorio;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfAddress;

    @FXML
    private Label lbFalseCpf;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lbAlreadyOnSystem;

    @FXML
    private TextField tfCPF;

    @FXML
    private Label lbTelefoneObrigatorio;

    @FXML
    private Label lbEnderecoObrigatorio;

    @FXML
    private ChoiceBox<String> cbTipo;

    @FXML
    private Label lbDataObrigatorio;

    @FXML
    private Label lbDataNascimento;




    public UpdateCustomerScenario(Customer customer2Update){
        super("fxml/scenario_update_customer.fxml");
        this.updateThisCustomer = customer2Update;
    }

    @FXML private void initialize()
    {

        cbTipo.setItems(personTypes);
        tfName.setText(this.updateThisCustomer.getName());
        tfCPF.setText(this.updateThisCustomer.getCpf_cnpj());
        if(this.updateThisCustomer.getType() == Person.Type.PHYSICAL)
        {
            cbTipo.getSelectionModel().select(0);
        }
        else
        {
            cbTipo.getSelectionModel().select(1);
        }
        dpDate.setValue(this.updateThisCustomer.getDate());
        tfAddress.setText(updateThisCustomer.getAddresses().get(0).getStreet());
        tfPhone.setText(updateThisCustomer.getPhones().get(0).getNumber());
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

            String name,  address , phone, cpf, tipo;
            name = tfName.getText();
            address = tfAddress.getText();
            phone = tfPhone.getText();
            cpf = tfCPF.getText();
            tipo = cbTipo.getValue();
            LocalDate date = dpDate.getValue();
            Person.Type tipo_cadastrar = Person.Type.LEGAL;
            boolean falseDocument = true;
            //pegando campos nulos
            boolean AlreadyOnSystem = false;

            for (Customer customer : customerManager.getAll()) {
                if (customer.getCpf_cnpj().equals(cpf)){
                    AlreadyOnSystem = true;
                    break;
                }
            }


            lbNomeObrigatorio.setVisible(name.isEmpty());
            lbDocumentoObrigatorio.setVisible(cpf.isEmpty());
            //lbTelefoneObrigatorio.setVisible(phone.isEmpty());
            //lbEnderecoObrigatorio.setVisible(address.isEmpty());
            //lbAlreadyOnSystem.setVisible(AlreadyOnSystem);
            lbFalseCpf.setVisible(!falseDocument);
            lbDataObrigatorio.setVisible(date == null);


            if (!tipo.isEmpty())
            {
                if (tipo.equals("FISICA"))
                {
                    tipo_cadastrar = Person.Type.PHYSICAL;
                    falseDocument = DocumentValidator.isValidCPF(cpf);
                }
                if (tipo.equals("JURIDICA"))
                {
                    tipo_cadastrar = Person.Type.LEGAL;
                    falseDocument = DocumentValidator.isValidCPNJ(cpf);
                }
                if (!falseDocument) lbFalseCpf.setVisible(true);
                if (falseDocument && !name.isEmpty() && !phone.isEmpty() && !address.isEmpty() && date != null) {
                    Address newAddress = new Address();
                    newAddress.setStreet(address);
                    Phone newPhone = new Phone();
                    newPhone.setNumber(phone);
                    this.updateThisCustomer.setName(name);
                    this.updateThisCustomer.setCpf_cnpj(cpf);
                    this.updateThisCustomer.setDate(date);
                    this.updateThisCustomer.getAddresses().remove(0);
                    this.updateThisCustomer.addAdress(newAddress);
                    this.updateThisCustomer.getPhones().remove(0);
                    this.updateThisCustomer.addPhone(newPhone);
                    putFeedback(FEEDBACK_NEW_CUSTOMER, this.updateThisCustomer);
                    processFeedbackAndFinish();
                }
            }
        });

        btCancel.setOnAction(event -> finish());

        setUpScenarioStyle(Scenario.ScenarioStyle.BETTER_UNDECORATED);

    }

}
