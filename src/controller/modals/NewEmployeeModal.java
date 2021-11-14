package controller.modals;

import java.util.ArrayList;
import java.util.List;

import controller.NewEntityModal;
import controller.screens.EmployeesScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Component;
import utils.Screen;
import view.ScreenLoader;

public class NewEmployeeModal extends NewEntityModal<TextField> {
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
            phoneNumber.setText(selectedEmployee.getPhoneNumber());

            cpf.setText(selectedEmployee.getCpf());
            cpf.setDisable(true);

            modalTitle.setText("Atualizar Funcionário");
            submitButton.setText("Atualizar");
        }
    }

    public void submit() {
        List<Component<TextField>> inputs = new ArrayList<Component<TextField>>();

        inputs.add(new Component<TextField>(name, "name"));
        inputs.add(new Component<TextField>(cpf, "cpf"));
        inputs.add(new Component<TextField>(phoneNumber, "phoneNumber"));
        inputs.add(new Component<TextField>(password, "password"));

        try {
            for(Component<TextField> input : inputs) {
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

            for(Component<TextField> input : inputs) {
                verifyInputError(input, message);
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }
}
