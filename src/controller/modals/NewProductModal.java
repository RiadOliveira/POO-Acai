package controller.modals;

import controller.DashboardModal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.BO.ProductBO;
import model.VO.ProductVO;
import utils.Category;
import utils.Screen;
import view.ScreenLoader;

public class NewProductModal extends DashboardModal {
    @FXML private TextField name;
    @FXML private TextField price;

    @FXML private Label errorText;

    @FXML HBox priceContainer;
    @FXML private ComboBox<Category> categoryBox;

    public void initialize() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(Category.values());

        categoryBox.setItems(categories);
    }

    public void confirmAdd() {
        try {
            verifyData();

            ProductVO product = new ProductVO();

            product.setName(name.getText());
            product.setPrice(Double.parseDouble(price.getText()));
            product.setCategory(categoryBox.getValue());

            ProductBO.insert(ScreenLoader.getLoggedUser(), product);

            ScreenLoader.load(Screen.productsScreen);
            closeModal();
        } catch (Exception err) {
            String message = err.getMessage();

            name.setStyle(name.getStyle() + "-fx-border-color: none;");
            categoryBox.setStyle(categoryBox.getStyle() + "-fx-border-color: none;");
            priceContainer.setStyle(priceContainer.getStyle() + "-fx-border-color: none;");

            if(message.contains("name")) {
                name.setStyle(name.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("category")) {
                categoryBox.setStyle(categoryBox.getStyle() + "-fx-border-color: red;");
            } 
            
            if(message.contains("price")) {
                priceContainer.setStyle(priceContainer.getStyle() + "-fx-border-color: red;");
            }
            
            errorText.setStyle(errorText.getStyle() + "-fx-opacity: 1;");
        }
    }

    private void verifyData() throws Exception  {
        if(name.getText().length() == 0) {
            throw new Exception("Empty name");
        }

        if(categoryBox.getValue() == null) {
            throw new Exception("Empty category");
        }

        if(price.getText().length() == 0 || !verifyPrice()) {
            throw new Exception("Invalid price");
        }
    }

    private boolean verifyPrice() throws NumberFormatException {
        try {
            String formmatedPrice = price.getText().replace(',', '.');
            Double.parseDouble(formmatedPrice);

            price.setText(formmatedPrice);

            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
