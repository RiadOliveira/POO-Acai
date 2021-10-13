package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LandingPage extends Application {
    public static void main(String args[]) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("landingPage.fxml"));
    
        Scene scene = new Scene(root);

        primaryStage.setTitle("Digi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
