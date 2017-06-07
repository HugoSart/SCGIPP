package scgipp.ui.main.customers.info;

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
import scgipp.service.customer_management.Customer;
import scgipp.service.user_management.Permissions;
import scgipp.ui.main.customers.info.properties.CustomerPropertiesUIManager;
import scgipp.ui.main.user.info.permissions.UserPermissionsUIManager;
import scgipp.ui.main.user.info.properties.UserPropertiesUIManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 05/06/2017.
 */
public class CustomerInfoUIController implements Initializable {

    private Customer customer;

    @FXML TreeView<String> treeOptions;
    @FXML Pane contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Customer customer) {
        this.customer = customer;
        initTreeOptions();
    }

    private void initTreeOptions() {
        TreeItem<String> root = new TreeItem<>(customer.getName());
        TreeItem<String> properties = new TreeItem<>("Propriedades");
            TreeItem<String> adresses = new TreeItem<>("Endereços");
            TreeItem<String> contact = new TreeItem<>("Contato");
        TreeItem<String> permissions = new TreeItem<>("Compras realizadas");

        root.getChildren().add(properties);
            properties.getChildren().add(adresses);
            properties.getChildren().add(contact);
        root.getChildren().add(permissions);

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

                        CustomerPropertiesUIManager manager = new CustomerPropertiesUIManager(customer);
                        manager.loadOnPane(contentPane);

                        break;
                    case "Endereços":

                        break;
                    case "Contato":

                        break;
                    case "Compras realizadas":

                        break;
                }

            }
        }

    }

    public void btOkActionHandler(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }


}
