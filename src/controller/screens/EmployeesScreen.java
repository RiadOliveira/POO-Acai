package controller.screens;

import java.util.List;

import controller.DashboardPageWithModal;
import controller.DashboardPagesRedirect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Modal;
import utils.Screen;
import view.ModalLoader;
import view.ScreenLoader;

public class EmployeesScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    @FXML private TableView<UserVO> employeesTable;

    @FXML private TableColumn<UserVO, String> name;
    @FXML private TableColumn<UserVO, String> cpf;
    @FXML private TableColumn<UserVO, String> phoneNumber;

    @FXML private Label errorMessage;

    private static UserVO selectedEmployee = null;

    public void initialize() {
        try {
            if(selectedEmployee != null) {
                selectedEmployee = null;
            }

            ObservableList<UserVO> employees = FXCollections.observableArrayList();
            List<UserVO> allEmployees = UserBO.findAll();
    
            employees.addAll(allEmployees);
            employeesTable.setItems(employees);
    
            name.setCellValueFactory(new PropertyValueFactory<UserVO, String>("name"));
            cpf.setCellValueFactory(new PropertyValueFactory<UserVO, String>("cpf"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<UserVO, String>("phoneNumber"));
        } catch (Exception err) {
            //Handle exception.
            System.out.println(err.getMessage());
        }
    }

    public UserVO getSelectedEmployee() {
        return selectedEmployee;
    }

    public void update() {
        int index = employeesTable.getSelectionModel().getFocusedIndex();

        try {
            selectedEmployee = employeesTable.getItems().get(index);
            ModalLoader.load(Modal.newEmployeeModal);            
        } catch (Exception err) {
            errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
        }
    }

    public void delete() {
        int index = employeesTable.getSelectionModel().getFocusedIndex();

        try {
            UserBO.delete(employeesTable.getItems().get(index));
            ScreenLoader.load(Screen.employeesScreen);
        } catch (Exception err) {
            errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
        }
    }

    public void openModal() {
        ModalLoader.load(Modal.newEmployeeModal);
    }
}
