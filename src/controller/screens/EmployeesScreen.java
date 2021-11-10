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
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Modal;
import view.ModalLoader;

public class EmployeesScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    @FXML private TableView<UserVO> employeesTable;

    @FXML private TableColumn<UserVO, String> name;
    @FXML private TableColumn<UserVO, String> cpf;
    @FXML private TableColumn<UserVO, String> phoneNumber;

    public void initialize() {
        ObservableList<UserVO> employees = FXCollections.observableArrayList();
        List<UserVO> allEmployees = UserBO.findAll();

        employees.addAll(allEmployees);
        employeesTable.setItems(employees);

        name.setCellValueFactory(new PropertyValueFactory<UserVO, String>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<UserVO, String>("cpf"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<UserVO, String>("phoneNumber"));
    }


    public void openModal() {
        ModalLoader.load(Modal.newEmployeeModal);
    }
}
