package controller.screens;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Screen;
import view.ScreenLoader;

public class Landing {
    @FXML private TextField cpfInput;
    @FXML private Pane cpfInputContainer;

    @FXML private TextField passwordInput;
    @FXML private Pane passwordInputContainer;

    public void signIn() {
        try {
            UserVO user = new UserVO();
    
            user.setCpf(cpfInput.getText());
            user.setPassword(passwordInput.getText());
    
            user = UserBO.signIn(user);

            ScreenLoader.setLoggedUser(user);
            ScreenLoader.load(Screen.homeScreen);
        } catch (Exception err) {
            cpfInputContainer.setStyle(cpfInputContainer.getStyle() + "-fx-border-color: red;");
            passwordInputContainer.setStyle(passwordInputContainer.getStyle() + "-fx-border-color: red;");
        }
    }
}
