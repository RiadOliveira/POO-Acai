package controller.modals;

import java.util.ArrayList;
import java.util.List;

import controller.NewEntityModal;
import controller.screens.CustomersScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.CustomerBO;
import model.VO.CustomerVO;
import utils.Component;
import utils.Screen;
import view.ScreenLoader;

public class NewCustomerModal extends NewEntityModal<TextField> {
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
        List<Component<TextField>> inputs = new ArrayList<Component<TextField>>();

        inputs.add(new Component<TextField>(name, "name"));
        inputs.add(new Component<TextField>(cpf, "cpf"));
        inputs.add(new Component<TextField>(address, "address"));
        inputs.add(new Component<TextField>(phoneNumber, "phoneNumber"));

        try {
            for(Component<TextField> input : inputs) {
                verifyData(input);
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

            for(Component<TextField> input : inputs) {
                verifyInputError(input, message);
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }
}
