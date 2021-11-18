package controller.screens;

import java.sql.SQLException;
import java.util.List;

import controller.DashboardPageWithTable;
import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.CustomerBO;
import model.VO.CustomerVO;
import utils.Modal;
import utils.Screen;
import view.ModalLoader;
import view.ScreenLoader;

public class CustomersScreen extends DashboardPagesRedirect implements DashboardPageWithTable {
    @FXML private TableView<CustomerVO> customersTable;

    @FXML private TableColumn<CustomerVO, String> name;
    @FXML private TableColumn<CustomerVO, String> cpf;
    @FXML private TableColumn<CustomerVO, String> address;
    @FXML private TableColumn<CustomerVO, String> phoneNumber;

    @FXML private Label adminName;
    @FXML private Label errorMessage;

    @FXML private TextField searchBar;

    private static CustomerVO selectedCustomer = null;
    private List<CustomerVO> allCustomers = null;

    public void initialize() {
		adminName.setText(admin.getName());

        if(selectedCustomer != null) {
            selectedCustomer = null;
        }

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> 
            handleSearchTable(newValue)
        );

        try {
            fillTable();
        } catch (SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }
    }

    public CustomerVO getSelectedCustomer() {
        return selectedCustomer;
    }

    private void handleSearchTable(String searchedText) {
        ObservableList<CustomerVO> customers = FXCollections.observableArrayList();
        customers.addAll(CustomerBO.findByName(allCustomers, searchedText));

        customersTable.setItems(customers);
    }

    private void fillTable() throws SQLException, ValidationException {
        ObservableList<CustomerVO> customers = FXCollections.observableArrayList();
        allCustomers = CustomerBO.findAll();

        customers.addAll(allCustomers);
        customersTable.setItems(customers);

        name.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("cpf"));
        address.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("phoneNumber"));
    }

    public void update() {
        int index = customersTable.getSelectionModel().getFocusedIndex();

        try {
            selectedCustomer = customersTable.getItems().get(index);
            ModalLoader.load(Modal.newCustomerModal);            
        } catch (Exception err) {
            errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
        }
    }

    public void showCustomerHistory() {
        int index = customersTable.getSelectionModel().getFocusedIndex();

        try {
            selectedCustomer = customersTable.getItems().get(index);
            ModalLoader.load(Modal.customerOrdersHistoricModal);
        } catch (Exception err) {
            errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
        }
    }

    public void delete() {
        int index = customersTable.getSelectionModel().getFocusedIndex();

        try {
            CustomerBO.delete(customersTable.getItems().get(index));
            ScreenLoader.load(Screen.customersScreen);
        } catch (Exception err) {
            errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
        }
    }

    public void openModal() {
        ModalLoader.load(Modal.newCustomerModal);
    }
}
