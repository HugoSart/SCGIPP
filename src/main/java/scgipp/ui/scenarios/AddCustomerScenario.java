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
import scgipp.service.validators.CEP.CepData;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.NodeCustomizer;
import scgipp.ui.exceptions.InvalidDataException;
import scgipp.ui.exceptions.NoInternetConnection;

import java.io.IOException;
import java.time.LocalDate;

import static scgipp.service.validators.Connection.InternetConnetion.netIsAvailable;

public class AddCustomerScenario extends FeedbackScenario{

    private CustomerManager customerManager = CustomerManager.getInstance();

    public static final String FEEDBACK_NEW_CUSTOMER = "new_customer";
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
    //@FXML private Label lCampoObrigatorio;
    @FXML private TextField tfCPF;
    @FXML private Label lbTelefoneObrigatorio;
    @FXML private Label lbEnderecoObrigatorio;
    @FXML private ChoiceBox<String> cbTipo;
    @FXML private Label lbDataObrigatorio;
    @FXML
    private TextField tfAddressNumber;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCEP;

    @FXML
    private Label lbEndereçoMsg;

    @FXML private Label lbTipoObrigatorio;

    @FXML private void initialize()
    {
        cbTipo.setItems(personTypes);
    }

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfState;


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

        tfCEP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (netIsAvailable()) {
                    if (newValue != null && newValue.length() >= 8) {
                        if (CepData.get().getUF(newValue) == null) {
                            tfCEP.clear();
                            lbEnderecoObrigatorio.setVisible(true);
                            throw new InvalidDataException("CEP");
                        }
                        else {
                            tfAddress.setText(CepData.get().getRua(newValue));
                            tfCity.setText(CepData.get().getCidade(newValue));
                            tfState.setText(CepData.get().getUF(newValue));
                            tfBairro.setText(CepData.get().getBairro(newValue));

                            //tf.setDisable(false);
                            //complementField.setDisable(false);
                        }
                    }
                    else {
                        tfAddress.clear();
                        tfCity.clear();
                        tfState.clear();
                        tfBairro.clear();

                        //numberField.setDisable(true);
                        //complementField.setDisable(true);
                    }
                }
                else {
                    tfCEP.clear();
                    throw new NoInternetConnection();
                }
            } catch (IOException e) {
                e.getMessage();
            } catch (NoInternetConnection | InvalidDataException noInternetConnection) {

            }
        });

        btOk.setOnAction(event -> {
            String name,  address , phone, cpf, tipo, addressnumber, addresscep, addressbairro, addresscity, addressstate;
            name = tfName.getText();
            address = tfAddress.getText();
            addressbairro = tfBairro.getText();
            addressnumber = tfAddressNumber.getText();
            addresscep = tfCEP.getText();
            addresscity = tfCity.getText();
            addressstate = tfState.getText();
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
            lbTelefoneObrigatorio.setVisible(phone.isEmpty());
            lbEnderecoObrigatorio.setVisible(address.isEmpty() || addressbairro.isEmpty() || addresscep.isEmpty() || addressnumber.isEmpty());
            lbAlreadyOnSystem.setVisible(AlreadyOnSystem);
            lbTipoObrigatorio.setVisible(cbTipo.getSelectionModel().isEmpty());
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
                if (falseDocument && !AlreadyOnSystem && !name.isEmpty() && !phone.isEmpty() && !addressbairro.isEmpty()
                    && !addresscep.isEmpty() && !addressnumber.isEmpty() && !address.isEmpty() && date != null) {
                    Address newAddress = new Address();
                    newAddress.setStreet(address);
                    newAddress.setPostalCode(addresscep);
                    newAddress.setNumber(addressnumber);
                    newAddress.setComplement(addressbairro);
                    newAddress.setCity(addresscity);
                    newAddress.setState(addressstate);
                    Phone newPhone = new Phone();
                    newPhone.setNumber(phone);
                    Customer newCustomer = new Customer(tipo_cadastrar, name, cpf, date);
                    newCustomer.addAdress(newAddress);
                    newCustomer.addPhone(newPhone);
                    putFeedback(FEEDBACK_NEW_CUSTOMER, newCustomer);
                    processFeedbackAndFinish();
                }
            }

        });

        btCancel.setOnAction(event -> finish());



        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);

    }
}
