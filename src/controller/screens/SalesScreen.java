package controller.screens;

import java.sql.SQLException;
import java.util.List;

import controller.DashboardPageWithTable;
import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.OrderBO;
import model.BO.OrderProductBO;
import model.BO.ProductBO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Modal;
import utils.OrderStatus;
import view.ModalLoader;

public class SalesScreen extends DashboardPagesRedirect implements DashboardPageWithTable {
	@FXML private TableView<ProductVO> productsTable;
	@FXML private TableView<OrderProductVO> selectedProductsTable;
	
	@FXML private TableColumn<ProductVO, String> name;
	@FXML private TableColumn<ProductVO, Double> price;

	@FXML private TableColumn<OrderProductVO, ProductVO> selectedProductName;
	@FXML private TableColumn<OrderProductVO, Integer> selectedProductQuantity;
	@FXML private TableColumn<OrderProductVO, ProductVO> selectedProductPrice;
	
	@FXML private Label totalPrice;
	
	private static ProductVO selectedProduct = null;
	private static OrderProductVO orderProduct = null;
	private List<ProductVO> allProducts = null;
	private static ObservableList<OrderProductVO> selectedProductsList = FXCollections.observableArrayList();
	private Double total = 0.0;
	private boolean isProductAlreadySelected = false;
	private int productIndex = 0;
	
	public void initialize() {
		try {
			selectedProductsList.clear();
			fillTable();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}
	
	public ObservableList<OrderProductVO> getSelectedProductsList() {
		return selectedProductsList;
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
	
	public void addToCart() {
		int index = productsTable.getSelectionModel().getFocusedIndex();
		orderProduct = new OrderProductVO();
		isProductAlreadySelected = false;
		
		try {
			selectedProduct = productsTable.getItems().get(index);
			orderProduct.setProduct(selectedProduct);
			orderProduct.setQuantity(1);
			
			selectedProductsList.forEach(product -> {
				if (product.getProduct().getName() == orderProduct.getProduct().getName()) {
					isProductAlreadySelected = true;
				}
			});
			
			if (isProductAlreadySelected) {
				productIndex = 0;
				selectedProductsList.forEach(product -> {
					if (product.getProduct().getName() == orderProduct.getProduct().getName()) {
						try {
							orderProduct.setQuantity(product.getQuantity() + 1);
							selectedProductsList.set(productIndex, orderProduct);
						} catch (ValidationException e) {
							e.printStackTrace();
						}
					}
					
					productIndex++;
				});
			} else {
				selectedProductsList.add(orderProduct);
			}
			
			selectedProductsTable.setItems(selectedProductsList);
			
			total += selectedProduct.getPrice();
			
			selectedProductName.setCellValueFactory(new PropertyValueFactory<OrderProductVO, ProductVO>("product"));
			selectedProductQuantity.setCellValueFactory(new PropertyValueFactory<OrderProductVO, Integer>("quantity"));
			selectedProductPrice.setCellValueFactory(new PropertyValueFactory<OrderProductVO, ProductVO>("product"));
			
			selectedProductName.setCellFactory(cell -> {
				return new TableCell<OrderProductVO, ProductVO>() {
	                @Override
	                protected void updateItem(ProductVO item, boolean empty) {
	                   super.updateItem(item, empty);
	                   
	                   if (empty) {
	                	   setText("");
	                   } else {
	                	   setText(item.getName());
	                   }
	                }
	            };
			});
			
			selectedProductPrice.setCellFactory(cell -> {
				return new TableCell<OrderProductVO, ProductVO>() {
					@Override
					protected void updateItem(ProductVO item, boolean empty) {
					   super.updateItem(item, empty);
	
					   if(empty) {
							setText("");
					   } else {
							Double productPrice = Double.valueOf(item.getPrice());

							int verifyNumber = productPrice.toString().split("\\.")[1].length();
							String extraZero = (verifyNumber == 1) ? "0" : "";
	
							setText("R$ " + productPrice.toString().replace('.', ',') + extraZero);
					   }
					}
				};
			 } 
			);

			int verifyNumber = total.toString().split("\\.")[1].length();
            String extraZero = (verifyNumber == 1) ? "0" : "";

            totalPrice.setText("R$ " + total.toString().replace('.', ',') + extraZero);
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}
	
	public void removeFromCart() {
		int index = selectedProductsTable.getSelectionModel().getFocusedIndex();
		
		try {
			orderProduct = selectedProductsTable.getItems().get(index);
			productIndex = 0;
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}

		int listIndex = selectedProductsList.indexOf(orderProduct);
		OrderProductVO selectedOrderProduct = selectedProductsList.get(listIndex);

		try {
			if(selectedOrderProduct.getQuantity() > 1) {
				selectedOrderProduct.setQuantity(selectedOrderProduct.getQuantity() - 1);
				selectedProductsList.set(listIndex, selectedOrderProduct);
			} else {
				selectedProductsList.remove(listIndex);
			}
		} catch (ValidationException err) {
			System.out.println(err.getMessage());
		}
		
		selectedProductsTable.setItems(selectedProductsList);
		selectedProductQuantity.setCellValueFactory(new PropertyValueFactory<OrderProductVO, Integer>("quantity"));
		
		if (total > 0) {
			total -= selectedProduct.getPrice();
		}
		
		int verifyNumber = total.toString().split("\\.")[1].length();
        String extraZero = (verifyNumber == 1) ? "0" : "";

        totalPrice.setText("R$ " + total.toString().replace('.', ',') + extraZero);
	}
	
	public void checkout() {
		try {
			ModalLoader.load(Modal.finishSaleModal);
		} catch(Exception err) {
			System.out.println(err);
		}
	}
	
	@Override
	public void update() {
	}

	@Override
	public void delete() {
	}

	@Override
	public void openModal() {
		ModalLoader.load(Modal.finishSaleModal);
	}
    
}
