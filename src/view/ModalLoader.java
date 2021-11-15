package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Modal;

public class ModalLoader {
    private static Stage modalStage;

    public static Stage getModalStage() {
        return modalStage;
    }

    public static void setModalStage(Stage selectedStage) {
        modalStage = selectedStage;
    }

    public static void load(Modal modalName) {
        try {
            if(modalStage == null) {
                Stage newStage = new Stage();
                setModalStage(newStage);
            }

            Parent root = FXMLLoader.load(ScreenLoader.class.getResource("./modals/" + modalName + ".fxml"));
            Scene scene = new Scene(root);

            modalStage.setTitle(modalName.toString());
            modalStage.setScene(scene);
            modalStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
    	
        modalStage.hide();
    }
}
