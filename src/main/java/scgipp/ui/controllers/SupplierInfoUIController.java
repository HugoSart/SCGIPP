package scgipp.ui.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import scgipp.service.Adress;
import scgipp.service.supplier_management.Supplier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import scgipp.service.Adress;
import scgipp.service.transportadora_management.Transportadora;

/**
 * Created by kira on 08/06/17.
 */
public class SupplierInfoUIController implements Initializable {

    @FXML TreeView<String> treeOptions;
    @FXML Pane contentPane;

    private Supplier sup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Supplier supAlvo) {
        this.sup = supAlvo;
        initTreeOptions();
    }

    public void btOkActionHandler(ActionEvent event) {((Node)event.getSource()).getScene().getWindow().hide();
    }

    private void initTreeOptions() {
        TreeItem<String> root = new TreeItem<>(sup.getName());
        TreeItem<String> endereco = new TreeItem<>("Endereco");
        for (Adress a : sup.getAdresses()) {
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
