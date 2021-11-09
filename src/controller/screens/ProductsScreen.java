package controller.screens;

import java.util.List;

import controller.DashboardPageWithModal;
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

public class ProductsScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    @FXML private TableView<ProductVO> productsTable;

    @FXML private TableColumn<ProductVO, String> name;
    @FXML private TableColumn<ProductVO, Category> category;
    @FXML private TableColumn<ProductVO, Double> price;

    public void initialize() {
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
                        setText(item.toString().replace('.', ','));
                   }
                }
            };
         } );
    }

    public void openModal() {
        ModalLoader.load(Modal.newProductModal);
    }
}
