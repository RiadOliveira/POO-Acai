package controller.modals;

import java.util.ArrayList;
import java.util.List;

import controller.DashboardModal;
import controller.screens.CustomersScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.CustomerBO;
import model.VO.CustomerVO;
import utils.Input;
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
        List<Input> inputs = new ArrayList<Input>();

        inputs.add(new Input(name, "name"));
        inputs.add(new Input(cpf, "cpf"));
        inputs.add(new Input(address, "address"));
        inputs.add(new Input(phoneNumber, "phoneNumber"));

        try {
            for(Input selectedInput : inputs) {
                verifyData(selectedInput);
            }

            CustomerVO customer = new CustomerVO();

            String formattedCpf = getOnlyNumbers(cpf.getText());
            String formattedPhoneNumber = getOnlyNumbers(phoneNumber.getText());

            customer.setName(name.getText());
            customer.setCpf(formattedCpf);
            customer.setAddress(address.getText());
            customer.setPhoneNumber(formattedPhoneNumber);

            if(selectedCustomer == null) {
                CustomerBO.insert(customer);
            } else {
                customer.setId(selectedCustomer.getId());
                CustomerBO.update(customer);
            }

            ScreenLoader.load(Screen.customersScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            for(Input selectedInput : inputs) {
                verifyInputError(selectedInput, message);
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }

    private void verifyData(Input selectedInput) throws Exception  {
        if(selectedInput.input.getText().length() == 0) {
            throw new Exception("Empty " + selectedInput.name);
        }
    }

    private void verifyInputError(Input selectedInput, String message) {
        if(message.contains(selectedInput.name)) {
            selectedInput.input.setStyle(selectedInput.input.getStyle() + "-fx-border-color: red;");
        } else {
            selectedInput.input.setStyle(selectedInput.input.getStyle() + "-fx-border-color: none;");
        }
    }

    private String getOnlyNumbers(String inputValue) {
        return inputValue.replaceAll("[^\\d.]", "").replaceAll("\\.", "");
    }
}
