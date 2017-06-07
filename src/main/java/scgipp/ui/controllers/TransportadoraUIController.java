package scgipp.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import scgipp.ui.manager.CustomersUIManager;
import scgipp.ui.manager.UserInfoUIManager;

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

    private TransportadoraManager transpManager;
    private ObservableList<Transportadora> observableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transpManager = new TransportadoraManager();
        observableList = FXCollections.observableList(transpManager.getAll());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTransportadora.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        tvTransportadora.setItems(observableList);

    }

    private void initViews() {
    }
}