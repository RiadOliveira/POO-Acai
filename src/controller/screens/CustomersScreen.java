package controller.screens;

import java.util.List;

import controller.DashboardPageWithModal;
import controller.DashboardPagesRedirect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.CustomerBO;
import model.VO.CustomerVO;
import utils.Modal;
import view.ModalLoader;

public class CustomersScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    @FXML private TableView<CustomerVO> customersTable;

    @FXML private TableColumn<CustomerVO, String> name;
    @FXML private TableColumn<CustomerVO, String> cpf;
    @FXML private TableColumn<CustomerVO, String> address;
    @FXML private TableColumn<CustomerVO, String> phoneNumber;

    public void initialize() {
        try {
            ObservableList<CustomerVO> customers = FXCollections.observableArrayList();
            List<CustomerVO> allCustomers = CustomerBO.findAll();
    
            customers.addAll(allCustomers);
            customersTable.setItems(customers);
    
            name.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("name"));
            cpf.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("cpf"));
            address.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("address"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<CustomerVO, String>("phoneNumber"));
        } catch (Exception err) {
            //Handle exception.
            System.out.println(err.getMessage());
        }
    }

    public void openModal() {
        ModalLoader.load(Modal.newCustomerModal);
    }
}
