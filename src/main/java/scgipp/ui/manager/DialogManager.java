package scgipp.ui.manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import scgipp.service.sale_management.SaleBudget;
import scgipp.service.user_management.User;
import scgipp.service.user_management.UserManager;

import java.util.Optional;

/**
 * Created by hugo_ on 03/06/2017.
 */
public class DialogManager {

    public static boolean askBudgetName(SaleBudget saleBudget) {

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Orçamento");
        dialog.setHeaderText("Salvar Orçamento");
        dialog.setContentText("Insira o indentificador deste orçamento:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> saleBudget.name = name);

        return result.isPresent();

    }

    public static void changePassword(User user) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Senha");
        dialog.setHeaderText("Alterar senha");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField password1 = new PasswordField();
        password1.setPromptText("Nova senha");
        PasswordField password2 = new PasswordField();
        password2.setPromptText("Confirmar senha");

        grid.add(new Label("Nova senha:"), 0, 0);
        grid.add(password1, 1, 0);
        grid.add(new Label("Confirmar senha:"), 0, 1);
        grid.add(password2, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node btConfirm = dialog.getDialogPane().lookupButton(loginButtonType);
        btConfirm.setDisable(true);

        btConfirm.addEventHandler(ActionEvent.ACTION, event -> {
            user.setPassword(password1.getText());
            (new UserManager()).update(user);
        });

        // Do some validation (using the Java 8 lambda syntax).
        password1.textProperty().addListener((observable, oldValue, newValue) -> btConfirm.setDisable(!password1.getText().equals(password2.getText())));

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(password1::requestFocus);

        dialog.show();

    }

}
