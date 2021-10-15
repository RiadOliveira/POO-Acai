package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Screen;

public class Screens extends Application {
    private static Stage primaryStage;

    public static void main(String args[]) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage selectedStage) {
        primaryStage = selectedStage;
    }

    public static void loadScreen(Screen screenName) {
        try {
            Parent root = FXMLLoader.load(Screens.class.getResource(screenName + ".fxml"));
        
            Scene scene = new Scene(root);
    
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);

        primaryStage.setTitle("Digi");
        loadScreen(Screen.landing);
        primaryStage.show();
    }
}
