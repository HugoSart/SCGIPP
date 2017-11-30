package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scgipp.service.entities.Devolution;
import scgipp.service.entities.Product;
import scgipp.service.entities.User;
import scgipp.service.managers.DevolutionManager;
import scgipp.service.managers.ProductManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableDevolution;
import scgipp.ui.visible.ObservableProduct;
import scgipp.ui.visible.ObservableUser;

import java.math.BigDecimal;
import java.util.List;

public class DevolutionFragment extends Fragment {

    private ProductManager productManager = ProductManager.getInstance();
    private DevolutionManager devolutionManager = DevolutionManager.getInstance();

    @FXML private TableView<ObservableDevolution> tvDevolution;
    @FXML private TableColumn<ObservableDevolution, Integer> tcId;
    @FXML private TableColumn<ObservableDevolution, Integer> tcSaleId;
    @FXML private TableColumn<ObservableDevolution, String> tcProductName;
    @FXML private TableColumn<ObservableDevolution, String> tcDevolutionDate;
    @FXML private TableColumn<ObservableDevolution, Boolean> tcRestored;
    @FXML private TableColumn<ObservableDevolution, Integer> tcQuantity;

    @FXML private TextField tfSearch;
    @FXML private Button btAddDevolution;
    @FXML private Button btRemoveDevolution;
    @FXML private Button btUpdateDevolution;

    @FXML private ObservableList<ObservableDevolution> devolutionObservableList;

    public DevolutionFragment() {
        super("fxml/fragment_devolution.fxml");
    }

    @Override
    protected void onCreateView() {

        List<Devolution> devolutionList = devolutionManager.getAll();
        for (Devolution devolution : devolutionList) {
            System.out.println(devolution);
        }

        devolutionObservableList = FXCollections.observableList(ObservableDevolution.devolutionListTAsObservableDevolutionList(devolutionList));
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcSaleId.setCellValueFactory(cellData -> cellData.getValue().saleIdProperty().asObject());
        tcProductName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tcDevolutionDate.setCellValueFactory(cellData -> cellData.getValue().devolutionDateProperty());
        tcDevolutionDate.setCellValueFactory(cellData -> cellData.getValue().restoredProperty().asString());
        tcQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        tvDevolution.setItems(devolutionObservableList);

        btAddDevolution.setOnAction(event -> {
            FeedbackScenario addDevolutionScenario = new AddDevolutionScenario();
            Spawner.startFeedbackScenario(addDevolutionScenario, 0, this, (requestCode, resultCode, data) -> {
                Devolution devolution = (Devolution)data.get(AddDevolutionScenario.FEEDBACK_NEW_DEVOLUTION);
                if (devolutionManager.addDevolution(devolution) != -1)
                    devolutionObservableList.add(new ObservableDevolution(devolution));
                tvDevolution.refresh();

            });
        });

        btRemoveDevolution.setOnAction(event -> {
            ObservableDevolution observableDevolution = tvDevolution.getSelectionModel().getSelectedItem();
            if(observableDevolution.getDevolution().getRestoreToStock()){
                Product product = observableDevolution.getDevolution().getProduct();
                product.setQuantity(product.getQuantity() + observableDevolution.getDevolution().getQuantity());
            }
            DevolutionManager.removeDevolution(observableDevolution.getDevolution());
            devolutionObservableList.remove(observableDevolution);
            tvDevolution.refresh();
        });

        btUpdateDevolution.setOnAction(event -> {
            ObservableDevolution observableDevolution = tvDevolution.getSelectionModel().getSelectedItem();
            FeedbackScenario updateDevolutionScenario = new UpdateDevolutionScenario();
            updateDevolutionScenario.putExtra("devolution", observableDevolution.getDevolution());
            Spawner.startFeedbackScenario(updateDevolutionScenario, 0, this, (requestCode, resultCode, data) -> {
                Devolution devolutionUpdated = (Devolution)data.get(UpdateDevolutionScenario.FEEDBACK_NEW_DEVOLUTION);
                devolutionManager.updateDevolution(devolutionUpdated);
                tvDevolution.refresh();
            });
        });

        tvDevolution.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                ObservableDevolution observableDevolution = tvDevolution.getSelectionModel().getSelectedItem();
            }
        });

        FilteredList<ObservableDevolution> filteredData = new FilteredList<>(devolutionObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getDevolution().getProduct().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getDevolution().getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getDevolution().getSale().getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvDevolution.setItems(filteredData);

    }




}