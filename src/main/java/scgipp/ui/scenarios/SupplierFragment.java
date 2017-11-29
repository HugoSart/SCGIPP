package scgipp.ui.scenarios;

import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import scgipp.service.entities.Supplier;
import scgipp.service.managers.SupplierManager;
import scgipp.service.validators.CEP.CepData;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.exceptions.*;
import scgipp.ui.visible.ObservableSupplier;
import java.io.IOException;
import java.util.List;

import static scgipp.service.validators.Connection.InternetConnetion.netIsAvailable;

public class SupplierFragment extends Fragment {

    @FXML private AnchorPane infoPane;
    @FXML private AnchorPane editPane;
    @FXML private AnchorPane helpPane;
    @FXML private TableView<ObservableSupplier> table;
    @FXML private TableColumn<ObservableSupplier, String> nameColumn;
    @FXML private TableColumn<ObservableSupplier, String> cnpjColumn;
    @FXML private TextField searchField;
    @FXML private TextField nameField;
    @FXML private TextField cnpjField;
    @FXML private TextField streetField;
    @FXML private TextField DDDField;
    @FXML private TextField telephoneField;
    @FXML private TextField numberField;
    @FXML private TextField complementField;
    @FXML private TextField districtField;
    @FXML private TextField cityField;
    @FXML private TextField stateField;
    @FXML private TextField countryField;
    @FXML private TextField postalCodeField;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button removeButton;
    @FXML private Button saveAddButton;
    @FXML private Button saveEditButton;
    @FXML private Button cancelButton;
    @FXML private Text supplierNameText;
    @FXML private Text supplierCnpjText;
    @FXML private Text supplierTelText;
    @FXML private Text supplierAddressText;
    @FXML private Shape retangle;

    @FXML private ObservableList<ObservableSupplier> observableSuppliers;

    private String name;
    private String cnpj;
    private String street;
    private String DDD;
    private String telephone;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String postalCode;

    private Alert alert = new Alert(Alert.AlertType.NONE);

    SupplierFragment() {
        super("fxml/fragment_supplier.fxml");
    }

    @Override
    protected void onCreateView() {
        setInitialGUIState();

        helpPane.setOnMouseClicked(event -> {
            WebView webView = new WebView();
            webView.setZoom(0.7);
            WebEngine engine = webView.getEngine();
            engine.load("http://sgeajuda.webnode.com/");


            VBox root = new VBox();
            root.getChildren().addAll(webView);

            Scene scene = new Scene(root, 1280,728);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        });

        addButton.setOnAction(event -> {
            editPane.setVisible(true);
            saveEditButton.setVisible(false);
            saveAddButton.setVisible(true);
        });

        saveAddButton.setOnAction(event -> {
            getFieldsData();

            try {
                if(name == null) {
                    throw new NullFieldException("Nome");
                }
                else {
                    if (notExistSameNameInBD(name)) {
                        if (DocumentValidator.isValidCPNJ(cnpj)) {
                            if (notExistSameCNPJNumberInBD(cnpj)) {
                                if (!city.isEmpty() && number.isEmpty()) {
                                    throw new NullFieldException("número do endereço");
                                }
                                else {
                                    SupplierManager.addSupplier(name, cnpj, new Address("Brasil", state, city, district, postalCode, street, number, complement), new Phone(DDD, telephone));
                                    successInsertionAlert();
                                    afterSaveGUIState();
                                }
                            } else {
                                cnpjField.clear();
                                throw new SameDataException("Fornecedor", "CNPJ");
                            }
                        }
                        else {
                            throw new InvalidDataException("CNPJ");
                        }
                    }
                    else {
                        nameField.clear();
                        throw new SameDataException("Fornecedor", "Nome");
                    }
                }
            }catch (Exception e) {
                e.getMessage();
            }finally {
                populateTable();
            }
        });

        cancelButton.setOnAction(event -> {
            cleanFields();
            editPane.setVisible(false);
        });

        table.setOnMousePressed(event -> {
            removeButton.setDisable(false);
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            name = observableSupplier.getSupplier().getName();
            cnpj = observableSupplier.supplierCNPJ(supplier);
            infoPane.setVisible(true);

            setInfoText(supplier);
            table.getSelectionModel().select(-1);
        });

        updateButton.setOnAction(event -> {
            editPane.setVisible(true);
            saveAddButton.setVisible(false);
            saveEditButton.setVisible(true);
            cnpjField.setDisable(true);

            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            setFieldsData(supplier);
        });

        saveEditButton.setOnAction(event1 -> {
            try {
                getFieldsData();

                if(name.length() == 0) {
                    throw new NullFieldException("Nome");
                }
                else {
                    if (!city.isEmpty() && number.isEmpty()) {
                        throw new NullFieldException("número do endereço");
                    }
                    else {
                        Supplier supplier = table.getSelectionModel().getSelectedItem().getSupplier();
                        SupplierManager.updateSupplier(supplier, name, new Address("Brasil", state, city, district, postalCode, street, number, complement),new Phone(DDD, telephone) );

                        successUpdateAlert();
                        afterSaveGUIState();
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            } finally {
                populateTable();
                cleanFields();
                cnpjField.setDisable(false);
                closeAnchorPanes();
            }
        });

        removeButton.setOnAction(event -> {
            infoPane.setVisible(true);

            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            name = observableSupplier.getSupplier().getName();
            cnpj = observableSupplier.supplierCNPJ(supplier);

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente deletar o fornecedor "+observableSupplier.getSupplier().getName() +"?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation.setHeaderText("Atenção!");
            confirmation.showAndWait();

            if (confirmation.getResult() == ButtonType.YES) {
                SupplierManager.removeSupplier(observableSupplier.getSupplier());
                observableSuppliers.remove(observableSupplier);
                table.refresh();

                successRemoveAlert();
                cleanFields();
                closeAnchorPanes();
            }
        });

        FilteredList<ObservableSupplier> filteredData = new FilteredList<>(observableSuppliers, p-> true);
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(myObject.getSupplier().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            //else if (String.valueOf(myObject.getSupplier().getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            //else if (String.valueOf(myObject.getSupplier().getCpf_cnpj()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        })));
        table.setItems(filteredData);

        postalCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (netIsAvailable()) {
                    if (newValue != null && newValue.length() >= 8) {
                        if (CepData.getUF(newValue) == null) {
                            postalCodeField.clear();
                            throw new InvalidDataException("CEP");
                        }
                        else {
                            streetField.setText(CepData.getRua(newValue));
                            cityField.setText(CepData.getCidade(newValue));
                            stateField.setText(CepData.getUF(newValue));
                            districtField.setText(CepData.getBairro(newValue));

                            numberField.setDisable(false);
                            complementField.setDisable(false);
                        }
                    }
                    else {
                        streetField.clear();
                        cityField.clear();
                        stateField.clear();
                        districtField.clear();

                        numberField.setDisable(true);
                        complementField.setDisable(true);
                    }
                }
                else {
                    postalCodeField.clear();
                    throw new NoInternetConnection();
                }
            } catch (IOException e) {
                e.getMessage();
            } catch (NoInternetConnection | InvalidDataException noInternetConnection) {

            }
        });
    }

    private void successRemoveAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Fornecedor removido com sucesso!");
        alert.showAndWait();
    }

    private void successUpdateAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Fornecedor atualizado com sucesso!");
        alert.showAndWait();
    }

    private void successInsertionAlert() {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Fornecedor cadastrado com sucesso!");
        alert.showAndWait();
    }

    private void setInitialGUIState() {
        supplierTelText.setVisible(false);
        supplierAddressText.setVisible(false);
        saveAddButton.setVisible(false);
        saveEditButton.setVisible(false);
        removeButton.setDisable(true);

        disableAddressFields();
        closeAnchorPanes();
        populateTable();
        configureSearchOption();
    }

    private void afterSaveGUIState() {
        cleanFields();
        populateTable();
        disableAddressFields();
        closeAnchorPanes();
    }

    private void setFieldsData(Supplier supplier) {
        nameField.setText(supplier.getName());
        cnpjField.setText(supplier.getCpf_cnpj());

        if (!supplier.getPhones().isEmpty()) {
            DDDField.setText(supplier.getPhones().get(0).getAreaCode());
            telephoneField.setText(supplier.getPhones().get(0).getNumber());
        }

        if (!supplier.getAddresses().isEmpty()) {
            streetField.setText(supplier.getAddresses().get(0).getStreet());
            numberField.setText(supplier.getAddresses().get(0).getNumber());
            districtField.setText(supplier.getAddresses().get(0).getDistrict());
            complementField.setText(supplier.getAddresses().get(0).getComplement());
            cityField.setText(supplier.getAddresses().get(0).getCity());
            stateField.setText(supplier.getAddresses().get(0).getState());
            //postalCodeField.setText(supplier.getAddresses().get(0).getPostalCode());
        }
    }

    private void disableAddressFields() {
        numberField.setDisable(true);
        complementField.setDisable(true);
        districtField.setDisable(true);
        cityField.setDisable(true);
        stateField.setDisable(true);
        streetField.setDisable(true);
    }

    private void setInfoText(Supplier supplier) {
        supplierNameText.setText(name);
        supplierCnpjText.setText(cnpj);

        if (!supplier.getAddresses().isEmpty()) {
            supplierAddressText.setVisible(true);
            supplierAddressText.setText(supplier.getAddresses().get(0).toString());
        }
        else {
            supplierAddressText.setVisible(false);
        }

        if (!supplier.getPhones().isEmpty()) {
            supplierTelText.setVisible(true);
            supplierTelText.setText(supplier.getPhones().get(0).toString());
        }
        else {
            supplierTelText.setVisible(false);
        }
    }

    private void closeAnchorPanes() {
        editPane.setVisible(false);
        infoPane.setVisible(false);
    }

    private void getFieldsData() {
        name = nameField.getText();
        cnpj = stringCleaner(cnpjField.getText());
        DDD = DDDField.getText();
        telephone = telephoneField.getText();
        street = streetField.getText();
        district = districtField.getText();
        number = numberField.getText();
        city = cityField.getText();
        state = stateField.getText();
        complement = complementField.getText();
    }

    private boolean notExistSameCNPJNumberInBD(String cnpj) {
        List<Supplier> supplierList = SupplierManager.getAll();

        for (Supplier supplier : supplierList) {
            if (supplier.getCpf_cnpj().equals(cnpj)) {
                return false;
            }
        }
        return true;
    }

    private boolean notExistSameNameInBD(String name) {
        List<Supplier> supplierList = SupplierManager.getAll();

        for (Supplier supplier : supplierList) {
            if (supplier.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private void configureSearchOption() {

    }

    private void populateTable(){
        List<Supplier> supplierList;
        supplierList = SupplierManager.getAll();

        observableSuppliers = FXCollections.observableList(ObservableSupplier.supplierListAsObervableSupplierList(supplierList));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cnpjColumn.setCellValueFactory(cellData -> cellData.getValue().cnpjProperty());
        table.setItems(observableSuppliers);
    }

    private String stringCleaner(String documentNumber) {
        StringBuilder stringCleaned = new StringBuilder();
        char c;

        for (int i = 0; i < documentNumber.length(); i++) {
            c = documentNumber.charAt(i);

            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                stringCleaned.append(c);
            }
        }
        return stringCleaned.toString();
    }

    private void cleanFields(){
        nameField.clear();
        cnpjField.clear();
        DDDField.clear();
        telephoneField.clear();
        streetField.clear();
        numberField.clear();
        cityField.clear();
        districtField.clear();
        stateField.clear();
        complementField.clear();
        postalCodeField.clear();
    }
}
