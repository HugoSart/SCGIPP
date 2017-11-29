package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

public class SameDataException extends PrintableException {

    public SameDataException(String entityName, String illegalData) {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = "Já existe um " + entityName + " com o mesmo " + illegalData + " cadastrado.";
        printExceptionMessage();
    }
}
