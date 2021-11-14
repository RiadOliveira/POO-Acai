package controller.modals;

import controller.DashboardModal;
import controller.screens.CustomersScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML private Label modalTitle;
    @FXML private Label errorText;

    @FXML private Button submitButton;

    private CustomerVO selectedCustomer = null;

    public void initialize() {
        CustomersScreen customersScreen = new CustomersScreen();
        selectedCustomer = customersScreen.getSelectedCustomer();

        System.out.println(selectedCustomer);

        if(selectedCustomer != null) {
            name.setText(selectedCustomer.getName());
            cpf.setText(selectedCustomer.getCpf());
            address.setText(selectedCustomer.getAddress());
            phoneNumber.setText(selectedCustomer.getPhoneNumber());

            modalTitle.setText("Atualizar Cliente");
            submitButton.setText("Atualizar");
        }
    }

    public void submit() {
        try {
            verifyData();

            CustomerVO customer = new CustomerVO();

            String formattedCpf = getOnlyNumbers(cpf.getText());
            String formattedPhoneNumber = getOnlyNumbers(phoneNumber.getText());

            customer.setId(selectedCustomer.getId());
            customer.setName(name.getText());
            customer.setCpf(formattedCpf);
            customer.setAddress(address.getText());
            customer.setPhoneNumber(formattedPhoneNumber);

            if(selectedCustomer == null) {
                CustomerBO.insert(customer);
            } else {
                CustomerBO.update(customer);
            }

            ScreenLoader.load(Screen.customersScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            name.setStyle(name.getStyle() + "-fx-border-color: none;");
            cpf.setStyle(cpf.getStyle() + "-fx-border-color: none;");
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

    private String getOnlyNumbers(String inputValue) {
        return inputValue.replaceAll("[^\\d.]", "").replaceAll("\\.", "");
    }
}
