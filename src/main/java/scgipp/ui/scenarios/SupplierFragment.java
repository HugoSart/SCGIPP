<<<<<<< HEAD
package scgipp.ui.scenarios;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.Supplier;
import scgipp.service.managers.SupplierManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.visible.ObservableSupplier;

import java.io.StringWriter;
import java.util.List;

public class SupplierFragment extends Fragment {

    @FXML private AnchorPane supplierInfoPane;
    @FXML private TableView<ObservableSupplier> table;
    @FXML private TableColumn<ObservableSupplier, String> nameColumn;
    @FXML private TableColumn<ObservableSupplier, String> cnpjColumn;
    @FXML private TextField searchField;
    @FXML private TextField nameField;
    @FXML private TextField cnpjField;
    @FXML private Label nameLabel;
    @FXML private Label cnpjLabel;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button removeButton;
    @FXML private Button saveButton;

    @FXML private ObservableList<ObservableSupplier> observableSuppliers;

    public SupplierFragment() {
        super("fxml/fragment_supplier.fxml");
    }

    @Override
    protected void onCreateView() {
        populateTable();

        FilteredList<ObservableSupplier> filteredData = new FilteredList<>(observableSuppliers, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getSupplier().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getSupplier().getCpf_cnpj()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        table.setItems(filteredData);

        addButton.setOnAction(event -> {
            String name = nameField.getCharacters().toString();
            String cnpj = stringCleaner(cnpjField.getCharacters().toString());
            Alert alert;
            boolean flag = false;

            if (DocumentValidator.isValidCPNJ(cnpj)) {
                List<Supplier> supplierList = SupplierManager.getAll();

                for (Supplier supplier : supplierList) {
                    if (supplier.getCpf_cnpj().equals(cnpj)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fornecedor já existente cadastrado. Edite o fornecedor ja existente caso queira.");
                    alert.showAndWait();
                }
                else {
                    SupplierManager.addSupplier(new Supplier(name, cnpj));
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Fornecedor cadastrado com sucesso!");
                    alert.showAndWait();
                    populateTable();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("CNPJ Inválido");
                alert.showAndWait();
            }
            cnpjField.setText("");
        });


        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
                nameField.setText(observableSupplier.getSupplier().getName());
                cnpjField.setText(observableSupplier.getSupplier().getCpf_cnpj());
            }
            cleanFields();
        });

        updateButton.setOnAction(event -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            Alert alert;

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
        });

        removeButton.setOnAction(event -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
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
=======
package scgipp.ui.scenarios;

import com.sun.javafx.binding.StringFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.Supplier;
import scgipp.service.managers.SupplierManager;
import scgipp.service.validators.DocumentValidator.DocumentValidator;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.visible.ObservableSupplier;

import java.io.StringWriter;
import java.util.List;

public class SupplierFragment extends Fragment {

    @FXML private AnchorPane supplierInfoPane;
    @FXML private TableView<ObservableSupplier> table;
    @FXML private TableColumn<ObservableSupplier, String> nameColumn;
    @FXML private TableColumn<ObservableSupplier, String> cnpjColumn;
    @FXML private TextField searchField;
    @FXML private TextField nameField;
    @FXML private TextField cnpjField;
    @FXML private Label nameLabel;
    @FXML private Label cnpjLabel;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button removeButton;
    @FXML private Button saveButton;

    @FXML private ObservableList<ObservableSupplier> observableSuppliers;

    public SupplierFragment() {
        super("fxml/fragment_supplier.fxml");
    }

    @Override
    protected void onCreateView() {
        populateTable();

        FilteredList<ObservableSupplier> filteredData = new FilteredList<>(observableSuppliers, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getSupplier().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getSupplier().getCpf_cnpj()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        table.setItems(filteredData);

        addButton.setOnAction(event -> {
            String name = nameField.getCharacters().toString();
            String cnpj = stringCleaner(cnpjField.getCharacters().toString());
            Alert alert;
            boolean flag = false;

            if (DocumentValidator.isValidCPNJ(cnpj)) {
                List<Supplier> supplierList = SupplierManager.getAll();

                for (Supplier supplier : supplierList) {
                    if (supplier.getCpf_cnpj().equals(cnpj)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fornecedor já existente cadastrado. Edite o fornecedor ja existente caso queira.");
                    alert.showAndWait();
                }
                else {
                    SupplierManager.addSupplier(new Supplier(name, cnpj));
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Fornecedor cadastrado com sucesso!");
                    alert.showAndWait();
                    populateTable();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("CNPJ Inválido");
                alert.showAndWait();
            }
            cnpjField.setText("");
        });


        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
                nameField.setText(observableSupplier.getSupplier().getName());
                cnpjField.setText(observableSupplier.getSupplier().getCpf_cnpj());
            }
            cleanFields();
        });

        updateButton.setOnAction(event -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
            Supplier supplier = observableSupplier.getSupplier();
            Alert alert;

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
        });

        removeButton.setOnAction(event -> {
            ObservableSupplier observableSupplier = table.getSelectionModel().getSelectedItem();
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
>>>>>>> [C]ObservableCustomer
