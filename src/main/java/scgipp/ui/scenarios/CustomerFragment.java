package scgipp.ui.scenarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

import java.time.LocalDate;
import java.util.List;

public class CustomerFragment extends Fragment {

    private CustomerManager customerManager = CustomerManager.getInstance();

    @FXML
    private AnchorPane customerInfoPane;

    @FXML private TableView<Customer> tvCustomers;
    @FXML private TableColumn<Customer, Integer> tcId;
    @FXML private TableColumn<Customer, String> tcName;
    @FXML private TableColumn<Customer, String> tcCPF;
    @FXML private TextField tfSearch;
    @FXML private Button btTest;
    @FXML private Button btAddCustomer;
    @FXML private Button btRemove;

    @FXML
    ObservableList<Customer> customerObservableList;

    public CustomerFragment() {
        super("fxml/fragment_customer.fxml");
    }

    @Override
    protected void onCreateView() {

        List<Customer> customerList = customerManager.getAll();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        customerInfoPane.setVisible(false);

        customerObservableList = FXCollections.observableList(customerList);
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tvCustomers.setItems(customerObservableList);

        btAddCustomer.setOnAction(event -> {
            FeedbackScenario addUserScenario = new AddUserScenario();
            Spawner.startFeedbackScenario(addUserScenario, 0, this, (requestCode, resultCode, data) -> {
                //Customer customer = (Customer)data.get(AddCustomerScenario.FEEDBACK_NEW_USER);
                Customer customer = new Customer(Person.Type.PHYSICAL, "static_test", "000000", LocalDate.now());
                if (customerManager.addCustomer(customer) != -1)
                    customerObservableList.add(customer);
                tvCustomers.refresh();
            });
        });

        btRemove.setOnAction(event -> {
            Customer customer = tvCustomers.getSelectionModel().getSelectedItem();
            customerManager.removeCustomer(customer);
            customerObservableList.remove(customer);
            tvCustomers.refresh();
            customerInfoPane.setVisible(false);
        });

        tvCustomers.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                customerInfoPane.setVisible(true);
                Customer customer = tvCustomers.getSelectionModel().getSelectedItem();
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                userInfoFragment.putExtra("customer", customer);
                Spawner.startFragment(userInfoFragment, getParentController(), customerInfoPane);
            }
        });

        FilteredList<Customer> filteredData = new FilteredList<>(customerObservableList, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(myObject -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) return true;
            else if (String.valueOf(myObject.getId()).toLowerCase().contains(lowerCaseFilter)) return true;
            return false;
        }));
        tvCustomers.setItems(filteredData);

    }
}
