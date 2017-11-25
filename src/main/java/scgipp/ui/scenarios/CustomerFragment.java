package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.id.uuid.CustomVersionOneStrategy;
import scgipp.service.entities.Customer;
import scgipp.service.entities.User;
import scgipp.service.entities.superclass.Person;
import scgipp.service.managers.CustomerManager;
import scgipp.service.managers.UserManager;
import scgipp.ui.FXScenario.FeedbackScenario;
import scgipp.ui.FXScenario.Fragment;
import scgipp.ui.FXScenario.Spawner;
import scgipp.ui.visible.ObservableCustomer;
import scgipp.ui.visible.ObservableUser;

import java.time.LocalDate;
import java.util.List;

public class CustomerFragment extends Fragment {

    private CustomerManager customerManager = CustomerManager.getInstance();

    //@FXML private AnchorPane customerInfoPane;

    @FXML private TableView<ObservableCustomer> tvCustomers;
    @FXML private TableColumn<ObservableCustomer, Integer> tcId;
    @FXML private TableColumn<ObservableCustomer, String> tcName;
    @FXML private TableColumn<ObservableCustomer, String> tcDocument;
    @FXML private TextField tfSearch;
    @FXML private Button btTest;
    @FXML private Button btAddCustomer;
    @FXML private Button btRemove;
    @FXML private TableColumn<ObservableCustomer, String> tcEndereco;
    @FXML private TableColumn<ObservableCustomer, String> tcTelefone;

    @FXML
    private Label lbDataNascimento;

    @FXML ObservableList<ObservableCustomer> customerObservableList;

    public CustomerFragment() {
        super("fxml/fragment_customer.fxml");
    }

    @Override
    protected void onCreateView() {

        List<Customer> customerList = customerManager.getAll();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        //customerInfoPane.setVisible(false);

        customerObservableList = FXCollections.observableList(ObservableCustomer.custumerListTAsObservableUserList(customerList));
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcDocument.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        //tcEndereco.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        tcTelefone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        tvCustomers.setItems(customerObservableList);

        btAddCustomer.setOnAction(event -> {
            FeedbackScenario addCustomerScenario = new AddCustomerScenario();
            Spawner.startFeedbackScenario(addCustomerScenario, 0, this, (requestCode, resultCode, data) -> {
                Customer customer = (Customer)data.get(AddCustomerScenario.FEEDBACK_NEW_CUSTOMER);
                if (customerManager.addCustomer(customer) != -1)
                    customerObservableList.add(new ObservableCustomer(customer));
                tvCustomers.refresh();
            });
        });

        btRemove.setOnAction(event -> {
            ObservableCustomer customer = tvCustomers.getSelectionModel().getSelectedItem();
            customerManager.removeCustomer(customer.getCustomer());
            customerObservableList.remove(customer);
            tvCustomers.refresh();
           // customerInfoPane.setVisible(false);
        });

        tvCustomers.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
               // customerInfoPane.setVisible(true);
                ObservableCustomer customer = tvCustomers.getSelectionModel().getSelectedItem();
                //UserInfoFragment userInfoFragment = new UserInfoFragment();
                //userInfoFragment.putExtra("customer", customer);
                //Spawner.startFragment(userInfoFragment, getParentController(), customerInfoPane);
            }
        });

        FilteredList<ObservableCustomer> filteredData = new FilteredList<>(customerObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getCustomer().getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getCustomer().getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getCustomer().getCpf_cnpj()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvCustomers.setItems(filteredData);

    }
}
