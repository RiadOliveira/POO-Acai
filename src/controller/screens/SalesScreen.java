package controller.screens;

import java.sql.SQLException;
import java.util.List;

import controller.DashboardPageWithTable;
import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.ProductBO;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Modal;
import view.ModalLoader;

public class SalesScreen extends DashboardPagesRedirect implements DashboardPageWithTable {
	@FXML private TableView<ProductVO> productsTable;
	@FXML private TableView<ProductVO> selectedProductsTable;
	
	@FXML private TableColumn<ProductVO, String> name;
	@FXML private TableColumn<ProductVO, Double> price;

	@FXML private TableColumn<ProductVO, String> selectedProductName;
	@FXML private TableColumn<ProductVO, Integer> selectedProductQuantity;
	@FXML private TableColumn<ProductVO, Double> selectedProductType;
	
	private static UserVO selectedEmployee = null;
	private List<ProductVO> allProducts = null;
	
	public void initialize() {
		if(selectedEmployee != null) {
            selectedEmployee = null;
        }
		
		try {
			fillTable();
		} catch (SQLException | ValidationException err) {
			System.out.println(err.getMessage());
		}
	}
	
	private void fillTable() throws SQLException, ValidationException {
        ObservableList<ProductVO> products = FXCollections.observableArrayList();
        allProducts = ProductBO.findAll();

        products.addAll(allProducts);
        productsTable.setItems(products);

        name.setCellValueFactory(new PropertyValueFactory<ProductVO, String>("name"));
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
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openModal() {
		// TODO Auto-generated method stub
		ModalLoader.load(Modal.finishSaleModal);
	}
    
}
