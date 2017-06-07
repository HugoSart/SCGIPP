package scgipp.ui.controllers;

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
import scgipp.service.user_management.Permissions;
import scgipp.service.user_management.User;
import scgipp.ui.manager.UserPermissionsUIManager;
import scgipp.ui.manager.UserPropertiesUIManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoUIController implements Initializable {

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

    public void btOkActionHandler(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    private void initTreeOptions() {
        TreeItem<String> root = new TreeItem<>(user.getLogin());
            TreeItem<String> properties = new TreeItem<>("Propriedades");
                //TreeItem<String> info = new TreeItem<>("Informações");
                //TreeItem<String> security = new TreeItem<>("Segurança");
            TreeItem<String> permissions = new TreeItem<>("Permissões");
                for (Permissions.Permission p : user.getPermissions().toSet()) {
                    TreeItem<String> i = new TreeItem<>(p.name());
                    permissions.getChildren().add(i);
                }
            TreeItem<String> history = new TreeItem<>("Histórico");

        root.getChildren().add(properties);
            //properties.getChildren().add(info);
            //properties.getChildren().add(security);
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
                    case "Propriedades":

                        UserPropertiesUIManager userPropertiesUIManager = new UserPropertiesUIManager(user);
                        userPropertiesUIManager.loadOnPane(contentPane);

                        break;
                    case "Permissões":

                        UserPermissionsUIManager userPermissionsUIManager = new UserPermissionsUIManager(user);
                        userPermissionsUIManager.loadOnPane(contentPane);

                        break;
                }

            }
        }

    }

}
