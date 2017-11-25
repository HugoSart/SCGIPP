package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import scgipp.service.entities.Supplier;
import scgipp.service.managers.SupplierManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.visible.ObservableSupplier;

import java.util.List;

public class SupplierFragment extends Fragment {

    @FXML private AnchorPane infoPane;
    @FXML private AnchorPane editPane;
    @FXML private TableView<ObservableSupplier> table;
    @FXML private TableColumn<ObservableSupplier, String> nameColumn;
    @FXML private TableColumn<ObservableSupplier, String> cnpjColumn;
    @FXML private TextField searchField;
    @FXML private TextField nameField;
    @FXML private TextField cnpjField;
    @FXML private TextField telephoneField;
    @FXML private TextField addressField;
    @FXML private Label nameLabel;
    @FXML private Label cnpjLabel;
    @FXML private Label telephoneLabel;
    @FXML private Label addressLabel;
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
    private Alert alert = new Alert(Alert.AlertType.NONE);

    SupplierFragment() {
        super("fxml/fragment_supplier.fxml");
    }

    @Override
    protected void onCreateView() {
        supplierTelText.setVisible(false);
        supplierAddressText.setVisible(false);
        saveAddButton.setVisible(false);
        saveEditButton.setVisible(false);

        closeAnchorPanes();
        populateTable();
        configureSearchOption();

        addButton.setOnAction(event -> {
            editPane.setVisible(true);
            saveAddButton.setVisible(true);
        });

        saveAddButton.setOnAction(event -> {
            getFieldsData();

            if(nameField.getText().length() == 0) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Preencha o campo Nome");
                alert.showAndWait();
            }
            else {
                if (notExistSameNameInBD(name)) {
                    if (DocumentValidator.isValidCPNJ(cnpj)) {
                        if (notExistSameCNPJNumberInBD(cnpj)) {
                            SupplierManager.addSupplier(new Supplier(name, cnpj));
                            // TODO: 24/11/17  Add address and Telephone values;

                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Fornecedor cadastrado com sucesso!");
                            alert.showAndWait();

                            populateTable();
                            closeAnchorPanes();
                        } else {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setHeaderText("Fornecedor já existente cadastrado. Edite o fornecedor ja existente caso queira.");
                            alert.showAndWait();
                        }
                    }
                    else {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setHeaderText("CNPJ Inválido");
                        alert.showAndWait();
                    }
                }
                else {
                    cleanFields();
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText("Já existe um fornecedor com esse nome cadastrado");
                    alert.showAndWait();
                }
            }
            cnpjField.setText("");
        });

        cancelButton.setOnAction(event -> {
            cleanFields();
            editPane.setVisible(false);
        });

        table.setOnMousePressed(event -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            name = observableSupplier.getSupplier().getName();
            cnpj = observableSupplier.supplierCNPJ(supplier);
            infoPane.setVisible(true);

            setInfoText();
            table.getSelectionModel().select(-1);
        });

        updateButton.setOnAction(event -> {
            editPane.setVisible(true);
            saveEditButton.setVisible(true);
        });

        saveEditButton.setOnAction(event1 -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();

            if (DocumentValidator.isValidCPNJ(cnpjField.getText())) {
                supplier.setName(nameField.getText());
                supplier.setCpf_cnpj(cnpjField.getText());

                SupplierManager.updateSupplier(supplier);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fornecedor atualizado com sucesso!");
                alert.showAndWait();

                populateTable();
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("CNPJ Inválido!");
                alert.showAndWait();
            }
            cleanFields();
            closeAnchorPanes();
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

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fornecedor removido com sucesso!");
                alert.showAndWait();

                cleanFields();
            }
        });
    }

    private void setInfoText() {
        supplierNameText.setText(name);
        supplierCnpjText.setText(cnpj);
    }

    private void closeAnchorPanes() {
        editPane.setVisible(false);
        infoPane.setVisible(false);
    }

    private void getFieldsData() {
        name = nameField.getCharacters().toString();
        cnpj = stringCleaner(cnpjField.getCharacters().toString());
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

        for (Supplier supplier : supplierList) {
            System.out.println(supplier.getName());
        }

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
        nameField.setText("");
        cnpjField.setText("");
    }
}
