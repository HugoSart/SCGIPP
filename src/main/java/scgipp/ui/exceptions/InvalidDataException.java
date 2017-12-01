package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

public class InvalidDataException extends PrintableException {

    public InvalidDataException(String invalidDataName) {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = invalidDataName + " inválido!";
        printExceptionMessage();
    }

    public InvalidDataException(String invalidDataName, String tam) {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = "O campo " + invalidDataName + " deve ter no máximo "+ tam +" caracteres.";
    }
}
