package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

public class NullFieldException extends PrintableException {

    public NullFieldException(String nullFieldName) {
        alert.setAlertType(Alert.AlertType.ERROR);
        printableMessage = "Prencha o campo " + nullFieldName;
        printExceptionMessage();
    }
}
