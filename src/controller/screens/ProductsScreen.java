package controller.screens;

import java.util.List;

import controller.DashboardPageWithTable;
import controller.DashboardPagesRedirect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.ProductBO;
import model.VO.ProductVO;
import utils.Category;
import utils.Modal;
import view.ModalLoader;

public class ProductsScreen extends DashboardPagesRedirect implements DashboardPageWithTable {
    @FXML private TableView<ProductVO> productsTable;

    @FXML private TableColumn<ProductVO, String> name;
    @FXML private TableColumn<ProductVO, Category> category;
    @FXML private TableColumn<ProductVO, Double> price;

    public void initialize() {
        try {
            ObservableList<ProductVO> products = FXCollections.observableArrayList();
            List<ProductVO> allProducts = ProductBO.findAll();
    
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
        } catch (Exception err) {
            //Handle exception.
            System.out.println(err.getMessage());
        }
    }

    public void update() {

    }

    public void delete() {
        
    }

    public void openModal() {
        ModalLoader.load(Modal.newProductModal);
    }
}
