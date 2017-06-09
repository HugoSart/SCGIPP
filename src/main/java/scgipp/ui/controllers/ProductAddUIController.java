package scgipp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import scgipp.service.product_management.Product;
import scgipp.service.product_management.ProductManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hugo_ on 08/06/2017.
 */
public class ProductAddUIController implements Initializable {

    @FXML private TextField tfType;
    @FXML private TextField tfName;
    @FXML private TextField tfCustomerPrice;
    @FXML private TextArea taDescription;
    @FXML private TextField tfAmount;
    @FXML private CheckBox cbBuyable;

    private Product product = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btAddActionHandler(ActionEvent event) {

        String name = tfName.getText(), description = taDescription.getText();
        Double customerPrice = Double.parseDouble(tfCustomerPrice.getText());
        int amount = Integer.parseInt(tfAmount.getText()), type = Integer.parseInt(tfType.getText());
        boolean buyable = cbBuyable.isSelected();

        ProductManager productManager = new ProductManager();

        if (product != null) {
            product.setAmout(amount);
            product.setCustomerPrice(customerPrice);
            product.setName(name);
            product.setDescription(description);
            product.setBuyable(buyable);
            product.setType(type);
            productManager.update(product);
            hide();
            return;
        }

        productManager.register(type, amount, name, description, customerPrice, buyable);

        hide();

    }

    public void btCancelActionHandler(ActionEvent event) {
        hide();
    }

    private void hide() {
        tfName.getParent().getScene().getWindow().hide();
    }

    public void initData(Product product) {
        this.product = product;
        if (product != null) {
            tfType.setText("" + product.getType());
            tfName.setText(product.getName());
            tfCustomerPrice.setText("" + product.getCustomerPrice());
            taDescription.setText(product.getDescription());
            cbBuyable.setSelected(product.isBuyable());
            tfAmount.setText("" + product.getAmount());
        }
    } //

}
