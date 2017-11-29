package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

public class InvalidDataException extends PrintableException {

    public InvalidDataException(String invalidDataName) {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = invalidDataName + " inválido!";
        printExceptionMessage();
    }
}
