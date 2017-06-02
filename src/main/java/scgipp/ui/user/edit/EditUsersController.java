package scgipp.ui.user.edit;

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
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.ui.user.edit.permissions.EditPermissionsManager;
import sun.reflect.generics.tree.Tree;

import java.net.URL;
import java.security.Security;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 01/06/2017.
 */
public class EditUsersController implements Initializable {

    @FXML TreeView<String> treeOptions;
    @FXML Pane contentPane;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(User user) {
        this.user = user;
        initTreeOptions();
    }

    private void initTreeOptions() {
        TreeItem<String> root = new TreeItem<>(user.getLogin());
            TreeItem<String> properties = new TreeItem<>("Propriedades");
                TreeItem<String> info = new TreeItem<>("Informações");
                TreeItem<String> security = new TreeItem<>("Segurança");
            TreeItem<String> permissions = new TreeItem<>("Permissões");
                for (Permissions.Permission p : user.getPermissions().toSet()) {
                    TreeItem<String> i = new TreeItem<>(p.name());
                    permissions.getChildren().add(i);
                }
            TreeItem<String> history = new TreeItem<>("Histórico");

        root.getChildren().add(properties);
            properties.getChildren().add(info);
            properties.getChildren().add(security);
        root.getChildren().add(permissions);
        root.getChildren().add(history);

        EventHandler<MouseEvent> mouseEventHandle = this::treeOptionsEventHandler;

        treeOptions.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

        treeOptions.setRoot(root);
    }

    private void treeOptionsEventHandler(MouseEvent event) {

        Node node = event.getPickResult().getIntersectedNode();

        // DOUBLE CLICK HANDLER
        if (event.getClickCount() == 2) {
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String name = (String) ((TreeItem)treeOptions.getSelectionModel().getSelectedItem()).getValue();

                switch (name) {
                    case "Permissões":

                        EditPermissionsManager editPermissionsManager = new EditPermissionsManager();
                        editPermissionsManager.loadOnPane(contentPane, user);

                        break;
                }

            }
        }

    }

}
