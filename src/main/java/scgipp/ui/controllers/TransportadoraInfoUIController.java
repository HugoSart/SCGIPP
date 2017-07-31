package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import scgipp.service.Address;
import scgipp.service.transportadora_management.Transportadora;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kira on 07/06/17.
 */
public class TransportadoraInfoUIController implements Initializable {

    @FXML
    TreeView<String> treeOptions;
    @FXML
    Pane contentPane;

    private Transportadora transp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Transportadora transpAlvo) {
        this.transp = transpAlvo;
        initTreeOptions();
    }

    public void btOkActionHandler(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    private void initTreeOptions() {
        TreeItem<String> root = new TreeItem<>(transp.getName());
        TreeItem<String> endereco = new TreeItem<>("Endereco");
        for (Address a : transp.getAddresses()) {
            TreeItem<String> i = new TreeItem<>(a.getCity());
            endereco.getChildren().add(i);
        }

        root.getChildren().add(endereco);

        EventHandler<MouseEvent> mouseEventHandle = this::treeOptionsEventHandler;

        treeOptions.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

        treeOptions.setRoot(root);
    }

    private void treeOptionsEventHandler(MouseEvent event) {

    }
}
