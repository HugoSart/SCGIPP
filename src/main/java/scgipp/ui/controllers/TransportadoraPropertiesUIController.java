package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import scgipp.service.transportadora_management.Transportadora;
import scgipp.service.user_management.User;
import scgipp.ui.manager.DialogManager;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kira on 08/06/17.
 */
public class TransportadoraPropertiesUIController implements Initializable{

    @FXML private Label lbUser;
    @FXML private Label lbId;

    private Transportadora transpAlvp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }


}
