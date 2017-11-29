package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

public class NoInternetConnection extends PrintableException {

    public NoInternetConnection() {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = "Não ha conexão com a internet para buscar o endereço. Tente novamente quando houver conexão.";
        printExceptionMessage();
    }
}
