package controller.modals;

import controller.DashboardModal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Screen;
import view.ScreenLoader;

public class NewEmployeeModal extends DashboardModal {
    @FXML private TextField name;
    @FXML private TextField cpf;
    @FXML private TextField phoneNumber;
    @FXML private PasswordField password;

    @FXML private Label errorText;

    public void confirmAdd() {
        try {
            verifyData();

            UserVO user = new UserVO();

            user.setName(name.getText());
            user.setCpf(cpf.getText().replaceAll("\\.", "").replace("-", ""));
            user.setPhoneNumber(phoneNumber.getText());
            user.setPassword(password.getText());

            UserBO.signUp(user);

            ScreenLoader.load(Screen.employeesScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            name.setStyle(name.getStyle() + "-fx-border-color: none;");
            cpf.setStyle(cpf.getStyle() + "-fx-border-color: none;");
            phoneNumber.setStyle(phoneNumber.getStyle() + "-fx-border-color: none;");
            password.setStyle(password.getStyle() + "-fx-border-color: none;");

            if(message.contains("name")) {
                name.setStyle(name.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("cpf")) {
                cpf.setStyle(cpf.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("phoneNumber")) {
                phoneNumber.setStyle(phoneNumber.getStyle() + "-fx-border-color: red;");
            }

            if(message.contains("password")) {
                password.setStyle(password.getStyle() + "-fx-border-color: red;");
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }

    private void verifyData() throws Exception  {
        if(name.getText().length() == 0) {
            throw new Exception("Empty name");
        }

        if(cpf.getText().length() == 0) {
            throw new Exception("Empty cpf");
        }

        if(phoneNumber.getText().length() == 0) {
            throw new Exception("Empty phoneNumber");
        }

        if(password.getText().length() == 0) {
            throw new Exception("Empty password");
        }
    }
}
