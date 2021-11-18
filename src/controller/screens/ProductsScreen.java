package controller.screens;

import java.sql.SQLException;
import java.util.List;

import controller.DashboardPageWithTable;
import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.ProductBO;
import model.VO.ProductVO;
import utils.Category;
import utils.Modal;
import view.ModalLoader;
import view.ScreenLoader;

public class ProductsScreen extends DashboardPagesRedirect implements DashboardPageWithTable {
    @FXML private TableView<ProductVO> productsTable;

    @FXML private TableColumn<ProductVO, String> name;
    @FXML private TableColumn<ProductVO, Category> category;
    @FXML private TableColumn<ProductVO, Double> price;
    
    @FXML private Label adminName;
    @FXML private Label errorMessage;

    @FXML Button newProductButton;

    @FXML private TextField searchBar;
    
    private static ProductVO selectedProduct = null;
    private List<ProductVO> allProducts = null;

    public void initialize() {
		adminName.setText(admin.getName());

        if(selectedProduct != null) {
            selectedProduct = null;
        }

        if(!ScreenLoader.getLoggedUser().getIsAdmin()) {
            newProductButton.setDisable(true);
            newProductButton.setStyle(newProductButton.getStyle() + "-fx-opacity: 0.8");;
        }

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> 
            handleSearchTable(newValue)
        );

        try {
            fillTable();
        } catch (SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }
    }

    public ProductVO getSelectedProduct() {
        return selectedProduct;
    }

    private void handleSearchTable(String searchedText) {
        ObservableList<ProductVO> products = FXCollections.observableArrayList();
        products.addAll(ProductBO.findByName(allProducts, searchedText));

        productsTable.setItems(products);
    }

    private void fillTable() throws SQLException, ValidationException {
        ObservableList<ProductVO> products = FXCollections.observableArrayList();
        allProducts = ProductBO.findAll();

        products.addAll(allProducts);
        productsTable.setItems(products);

        name.setCellValueFactory(new PropertyValueFactory<ProductVO, String>("name"));
        category.setCellValueFactory(new PropertyValueFactory<ProductVO, Category>("category"));
        price.setCellValueFactory(new PropertyValueFactory<ProductVO, Double>("price"));
        
        price.setCellFactory(cell -> {
            return new TableCell<ProductVO, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                   super.updateItem(item, empty);

                   if(empty) {
                        setText("");
                   } else {
                        int verifyNumber = item.toString().split("\\.")[1].length();
                        String extraZero = (verifyNumber == 1) ? "0" : "";

                        setText("R$ " + item.toString().replace('.', ',') + extraZero);
                   }
                }
            };
         } 
        );
    }

    public void update() {
    	int index = productsTable.getSelectionModel().getFocusedIndex();

        selectedProduct = productsTable.getItems().get(index);
        ModalLoader.load(Modal.newProductModal);
    }

    public void delete() {
    	int index = productsTable.getSelectionModel().getFocusedIndex();

    	try {
    		ProductBO.delete(ScreenLoader.getLoggedUser(), productsTable.getItems().get(index));
            productsTable.refresh();
    	} catch (Exception err) {
    		errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
    	}
    }

    public void openModal() {
        ModalLoader.load(Modal.newProductModal);
    }
}
