package controller.modals;

import controller.DashboardModal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.CustomerBO;
import model.VO.CustomerVO;
import utils.Screen;
import view.ScreenLoader;

public class NewCustomerModal extends DashboardModal {
    @FXML private TextField name;
    @FXML private TextField cpf;
    @FXML private TextField address;
    @FXML private TextField phoneNumber;

    @FXML private Label errorText;

    public void confirmAdd() {
        try {
            verifyData();

            CustomerVO customer = new CustomerVO();

            customer.setName(name.getText());
            customer.setCpf(cpf.getText().replaceAll("\\.", "").replace("-", ""));
            customer.setAddress(address.getText());
            customer.setPhoneNumber(phoneNumber.getText());

            CustomerBO.insert(customer);

            ScreenLoader.load(Screen.customersScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            name.setStyle(name.getStyle() + "-fx-border-color: none;");
            address.setStyle(address.getStyle() + "-fx-border-color: none;");
            phoneNumber.setStyle(phoneNumber.getStyle() + "-fx-border-color: none;");

            if(message.contains("name")) {
                name.setStyle(name.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("cpf")) {
                cpf.setStyle(cpf.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("address")) {
                address.setStyle(address.getStyle() + "-fx-border-color: red;");
            }

            if(message.contains("phoneNumber")) {
                phoneNumber.setStyle(phoneNumber.getStyle() + "-fx-border-color: red;");
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

        if(address.getText().length() == 0) {
            throw new Exception("Empty address");
        }

        if(phoneNumber.getText().length() == 0) {
            throw new Exception("Empty phoneNumber");
        }
    }
}
