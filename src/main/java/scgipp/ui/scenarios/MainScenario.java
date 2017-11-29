package scgipp.ui.scenarios;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import scgipp.data.hibernate.BaseEntity;
import scgipp.data.hibernate.DBConnection;
import scgipp.data.hibernate.DBManager;
import scgipp.service.UserSession;
import scgipp.ui.FXScenario.*;

/**
 * User: hugo_<br/>
 * Date: 04/10/2017<br/>
 * Time: 22:00<br/>
 */
public class MainScenario extends Scenario {

    @FXML private Pane rootPane;

    @FXML private HBox menuBar;
    @FXML private Button btExit;
    @FXML private Button btMaximize;
    @FXML private Button btHide;
    @FXML private Button btLogout;
    @FXML private Label lbSessionUser;

    @FXML private AnchorPane pUsers;
    @FXML private AnchorPane pCustomers;
    @FXML private AnchorPane pSales;
    @FXML private AnchorPane pSupplier;
    @FXML private AnchorPane pProduct;

    @FXML private TextField tfSearch;
    @FXML private TableView<BaseEntity> tvEntities;
    @FXML private TableColumn<BaseEntity, String> tcId;
    @FXML private TableColumn<BaseEntity, String> tcClass;
    @FXML private TableColumn<BaseEntity, String> tcInfo;
    @FXML private Button btRecovery;
    @FXML private Button btForget;
    private ObservableList<BaseEntity> observableEntities;

    public MainScenario() {
        super("fxml/scenario_main.fxml");
    }

    @Override
    protected void onConfigScene(Scene scene) {
        scene.getStylesheets().add("css/Style.css");
        lbSessionUser.setText(UserSession.getSession().getActiveUser().getLogin());

        Spawner.startFragment(new UserFragment(), this, pUsers);
        Spawner.startFragment(new CustomerFragment(), this, pCustomers);
        Spawner.startFragment(new SalesFragment(), this, pSales);
        Spawner.startFragment(new SupplierFragment(), this, pSupplier);
        Spawner.startFragment(new ProductFragment(), this, pProduct);

        btLogout.setOnAction(event -> {
            UserSession.getSession().closeSession();
            LoginScenario loginScenario = new LoginScenario();
            Spawner.startScenario(loginScenario, this);
            finish();
        });

        setUpRecoveryTab();

    }

    @Override
    protected void onConfigStage(Stage stage) {
        NodeCustomizer.setUpMenuBar(this, menuBar, btExit, btMaximize, btHide);
        setUpScenarioStyle(ScenarioStyle.BETTER_UNDECORATED);
    }

    private void setUpRecoveryTab() {

        observableEntities = FXCollections.observableArrayList(DBConnection.manager().recoveryAll());
        System.out.println(DBConnection.manager().recoveryAll());
        tcId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId().toString()));
        tcClass.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClass().getSimpleName()));
        tcInfo.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().toString()));
        tvEntities.setItems(observableEntities);

        btRecovery.setOnAction(event -> {
            BaseEntity entity = tvEntities.getSelectionModel().getSelectedItem();
            entity.recover();
            DBConnection.manager().update(entity);
            observableEntities.remove(entity);
            tvEntities.refresh();
        });

    }

}
