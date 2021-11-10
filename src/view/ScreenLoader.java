package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.VO.UserVO;
import utils.Screen;

public class ScreenLoader extends Application {
    private static Stage primaryStage;
    private static UserVO loggedUser;

    public static void main(String args[]) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage selectedStage) {
        primaryStage = selectedStage;
    }

    public static UserVO getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(UserVO user) {
        loggedUser = user;
    }

    public static void load(Screen screenName) {
        try {
            Parent root = FXMLLoader.load(ScreenLoader.class.getResource("./screens/" + screenName + ".fxml"));
        
            Scene scene = new Scene(root);
    
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);

        primaryStage.setTitle("Digi");
        load(Screen.landing);
        primaryStage.show();
    }
}
