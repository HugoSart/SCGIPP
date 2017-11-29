package scgipp.ui.exceptions;

import javafx.scene.control.Alert;

abstract class PrintableException extends Exception {

    Alert alert = new Alert(Alert.AlertType.NONE);
    String printableMessage;

    void printExceptionMessage() {
        alert.setHeaderText(printableMessage);
        alert.showAndWait();
    }
}
