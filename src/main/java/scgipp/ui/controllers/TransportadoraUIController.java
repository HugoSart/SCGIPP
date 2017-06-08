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
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.transportadora_management.TransportadoraManager;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;
import scgipp.ui.manager.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kira on 07/06/17.
 */
public class TransportadoraUIController implements Initializable {

    @FXML private AnchorPane mainPane;

    @FXML private Button btAdd;
    @FXML private Button btRemove;
    @FXML private Button btClose;

    @FXML private TableView<Transportadora> tvTransportadora;
    @FXML private TableColumn<Transportadora, Integer> tcId;
    @FXML private TableColumn<Transportadora, String> tcTransportadora;
    @FXML private TableColumn<Transportadora, String> tcCnpj;
    @FXML private TableColumn<Transportadora, String> tcTelefone;


    private TransportadoraManager transpManager;
    private TransportadoraInfoUIManager transpInfoUIManager;
    private ObservableList<Transportadora> observableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transpManager = new TransportadoraManager();
        observableList = FXCollections.observableList(transpManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTransportadora.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCnpj.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcTelefone.setCellValueFactory(new PropertyValueFactory<>("phones"));


        tvTransportadora.setItems(observableList);

        tvTransportadora.setRowFactory( tv -> {
            TableRow<Transportadora> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Transportadora rowData = row.getItem();
                    transpInfoUIManager = new TransportadoraInfoUIManager(rowData);
                    Stage stage = transpInfoUIManager.newStage();
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
        AddTransportadoraUIManager manajer = new AddTransportadoraUIManager();
        Stage stage = manajer.newStage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().focusedProperty().addListener((observable, oldValue, newValue) -> updateTable());
    }

    public void btRemoveActionHandler(ActionEvent event) throws IOException{
        TransportadoraManager transpManager = new TransportadoraManager();
        btRemove.setOnAction(e -> {
            Transportadora selectedItem = tvTransportadora.getSelectionModel().getSelectedItem();
            transpManager.remove(selectedItem);
            tvTransportadora.getItems().remove(selectedItem);
        });
    }

    private void updateTable() {
        observableList = FXCollections.observableList(transpManager.getAll());
        tvTransportadora.setItems(observableList);
        tvTransportadora.refresh();
    }


}