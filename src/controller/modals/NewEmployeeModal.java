package controller.modals;

import java.util.ArrayList;
import java.util.List;

import controller.DashboardModal;
import controller.screens.EmployeesScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Input;
import utils.Screen;
import view.ScreenLoader;

public class NewEmployeeModal extends DashboardModal {
    @FXML private TextField name;
    @FXML private TextField cpf;
    @FXML private TextField phoneNumber;
    @FXML private PasswordField password;

    @FXML private Label modalTitle;
    @FXML private Label errorText;

    @FXML private Button submitButton;

    private UserVO selectedEmployee = null;

    public void initialize() {
        EmployeesScreen employeesScreen = new EmployeesScreen();
        selectedEmployee = employeesScreen.getSelectedEmployee();

        if(selectedEmployee != null) {
            name.setText(selectedEmployee.getName());
            cpf.setText(selectedEmployee.getCpf());
            phoneNumber.setText(selectedEmployee.getPhoneNumber());

            modalTitle.setText("Atualizar Funcionário");
            submitButton.setText("Atualizar");
        }
    }

    public void submit() {
        List<Input> inputs = new ArrayList<Input>();

        inputs.add(new Input(name, "name"));
        inputs.add(new Input(cpf, "cpf"));
        inputs.add(new Input(phoneNumber, "phoneNumber"));
        inputs.add(new Input(password, "password"));

        try {
            for(Input input : inputs) {
                verifyData(input);
            }

            UserVO user = new UserVO();

            String formattedCpf = getOnlyNumbers(cpf.getText());
            String formattedPhoneNumber = getOnlyNumbers(phoneNumber.getText());

            user.setName(name.getText());
            user.setCpf(formattedCpf);
            user.setPhoneNumber(formattedPhoneNumber);
            user.setPassword(password.getText());

            if(selectedEmployee == null) {
                UserBO.signUp(user);
            } else {
                user.setId(selectedEmployee.getId());
                UserBO.update(user);
            }

            ScreenLoader.load(Screen.employeesScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            for(Input input : inputs) {
                verifyInputError(input, message);
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }

    private void verifyData(Input input) throws Exception  {
        if(input.component.getText().length() == 0) {
            throw new Exception("Empty " + input.name);
        }
    }

    private void verifyInputError(Input input, String message) {
        if(message.contains(input.name)) {
            input.component.setStyle(input.component.getStyle() + "-fx-border-color: red;");
        } else {
            input.component.setStyle(input.component.getStyle() + "-fx-border-color: none;");
        }
    }

    private String getOnlyNumbers(String inputValue) {
        return inputValue.replaceAll("[^\\d.]", "").replaceAll("\\.", "");
    }
}
