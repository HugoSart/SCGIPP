package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scgipp.service.supplier_management.Supplier;
import scgipp.service.supplier_management.SupplierManager;
import scgipp.ui.manager.AddSupplierUIManager;
import scgipp.ui.manager.SupplierInfoUIManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kira on 08/06/17.
 */
public class SupplierUIController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML private Button btAdd;
    @FXML private Button btRemove;

    @FXML private TableView<Supplier> tvSupplier;
    @FXML private TableColumn<Supplier, Integer> tcId;
    @FXML private TableColumn<Supplier, String> tcSupplier;
    @FXML private TableColumn<Supplier, String> tcTelefone;

    private SupplierManager supManager;
    private SupplierInfoUIManager supInfoUIManager;
    private ObservableList<Supplier> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        supManager = new SupplierManager();
        observableList = FXCollections.observableList(supManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSupplier.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcTelefone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tvSupplier.setItems(observableList);

        tvSupplier.setRowFactory( tv -> {
            TableRow<Supplier> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Supplier rowData = row.getItem();
                    supInfoUIManager = new SupplierInfoUIManager(rowData);
                    Stage stage = supInfoUIManager.newStage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.show();
                }
            });
            return row ;
        });

    }

    public void btCloseActionHandler(ActionEvent event) {
        mainPane.getScene().getWindow().hide();
    }

    public void btAddActionHandler(ActionEvent event) throws IOException {
        AddSupplierUIManager manajer = new AddSupplierUIManager();
        Stage stage = manajer.newStage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().focusedProperty().addListener((observable, oldValue, newValue) -> updateTable());

    }

    public void btRemoveActionHandler(ActionEvent event) {
        SupplierManager sManager = new SupplierManager();
        btRemove.setOnAction(e -> {
            Supplier selectedItem = tvSupplier.getSelectionModel().getSelectedItem();
            sManager.remove(selectedItem);
            tvSupplier.getItems().remove(selectedItem);
        });
    }

    private void updateTable() {
        observableList = FXCollections.observableList(supManager.getAll());
        tvSupplier.setItems(observableList);
        tvSupplier.refresh();
    }


}
