package controller.screens;

import java.sql.SQLException;
import java.util.List;

import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.BO.CustomerBO;
import model.BO.OrderBO;
import model.BO.ProductBO;
import model.VO.CustomerVO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import utils.Modal;
import utils.OrderStatus;
import view.ModalLoader;

public class HomeScreen extends DashboardPagesRedirect {
	@FXML private TableView<OrderVO> onHoldTable;
	@FXML private TableView<OrderVO> preparingTable;
	@FXML private TableView<OrderVO> doneTable;

	@FXML private TableColumn<OrderVO, CustomerVO> onHoldCustomer;
	@FXML private TableColumn<OrderVO, CustomerVO> onPrepCustomer;
	@FXML private TableColumn<OrderVO, CustomerVO> doneCustomer;
	
	@FXML private Label errorMessage;

	@FXML private ComboBox<String> filter;

	@FXML private DatePicker selectDate;
	@FXML private ComboBox<CustomerVO> selectCustomer;
	@FXML private ComboBox<ProductVO> selectProduct;

	private static OrderVO selectedOrder= null;
	private List<OrderVO> allOnHoldOrders = null;
	private List<OrderVO> allPreparingOrders = null;
	private List<OrderVO> allDoneOrders = null;
	private static String prevFilterValue = "Por Cliente";

	public void initialize() {
		selectDate.setManaged(false);
		selectDate.setStyle("-fx-opacity: 0;");

		selectProduct.setManaged(false);
		selectProduct.setStyle("-fx-opacity: 0;");

		ObservableList<String> filterTypes = FXCollections.observableArrayList();
		filterTypes.addAll("Por Cliente", "Por Produto", "Por Data");

		filter.setItems(filterTypes);
		filter.setValue("Por Cliente");

		try {
			setCustomerItems();
			fillOnHoldTable();
			fillPreparingTable();
			fillDoneTable();
		} catch (SQLException | ValidationException err) {
			System.out.println(err.getMessage());
		}
	}
	
	public OrderVO getSelectedOrder() {
		return selectedOrder;
	}

	public void changeType() {
		switch (prevFilterValue) {
			case "Por Cliente": {
				selectCustomer.setItems(null);
				changeFieldActivity(false, selectCustomer);
			}
			break;

			case "Por Produto": {
				selectProduct.setItems(null);
				changeFieldActivity(false, selectProduct);
			}
			break;

			case "Por Data": changeFieldActivity(false, selectDate);
		}

		switch (filter.getValue()) {
			case "Por Cliente": {
				changeFieldActivity(true, selectCustomer);
				selectCustomer.setStyle(selectCustomer.getStyle() + "-fx-background-color: #fff;");
				setCustomerItems();
			}
			break;

			case "Por Produto": {
				changeFieldActivity(true, selectProduct);
				selectProduct.setStyle(selectProduct.getStyle() + "-fx-background-color: #fff;");
				setProductsItems();
			}
			break;

			case "Por Data": changeFieldActivity(true, selectDate);
		}

		prevFilterValue = filter.getValue();
	}

	private void setCustomerItems() {
		ObservableList<CustomerVO> customers = FXCollections.observableArrayList();

		StringConverter<CustomerVO> converter = new StringConverter<CustomerVO>() {
			@Override
			public String toString(CustomerVO object) {
				return object == null ? "" : object.getName();
			}

			@Override
			public CustomerVO fromString(String string) {
				return null;
			}
		};

		try {
			customers.addAll(CustomerBO.findAll());

			selectCustomer.setConverter(converter);

			selectCustomer.setItems(customers);
			selectCustomer.setCellFactory(cell -> {
				return new ListCell<CustomerVO>() {
					@Override
					protected void updateItem(CustomerVO item, boolean empty) {
						super.updateItem(item, empty);

						if(empty) {
							setText("");
						} else {    
							setText(item.getName());
						}
					}
				};
				} );
		} catch (SQLException | ValidationException err) {
			System.out.println(err.getMessage());
		}
	}

	private void setProductsItems() {
		ObservableList<ProductVO> products = FXCollections.observableArrayList();

		StringConverter<ProductVO> converter = new StringConverter<ProductVO>() {
			@Override
			public String toString(ProductVO object) {
				return object == null ? "" : object.getName();
			}

			@Override
			public ProductVO fromString(String string) {
				return null;
			}
		};

		try {
			products.addAll(ProductBO.findAll());

			selectProduct.setConverter(converter);

			selectProduct.setItems(products);
			selectProduct.setCellFactory(cell -> {
				return new ListCell<ProductVO>() {
					@Override
					protected void updateItem(ProductVO item, boolean empty) {
						super.updateItem(item, empty);

						if(empty) {
							setText("");
						} else {    
							setText(item.getName());
						}
					}
				};
				} );
		} catch (SQLException | ValidationException err) {
			System.out.println(err.getMessage());
		}
	}

	private void changeFieldActivity(boolean toActivate, Control field) {
		if(toActivate) {
			field.setManaged(true);
			field.setStyle("-fx-opacity: 1;");
			field.toFront();
		} else {
			field.setManaged(false);
			field.setStyle("-fx-opacity: 0;");
			field.toBack();
		}
	}

	public void sendToPreparation() {
		int index = onHoldTable.getSelectionModel().getFocusedIndex();

		try {
			selectedOrder = onHoldTable.getItems().get(index);
			selectedOrder.setOrderStatus(OrderStatus.Preparando);

			System.out.println(selectedOrder.getCustomer().getName());
			OrderBO.update(selectedOrder);

			fillOnHoldTable();
			fillPreparingTable();			
		} catch (Exception err) {
			System.out.println(err);
		}
	}

	public void sendToDone() {
		int index = preparingTable.getSelectionModel().getFocusedIndex();

		try {
			selectedOrder = preparingTable.getItems().get(index);
			selectedOrder.setOrderStatus(OrderStatus.Pronto);

			OrderBO.update(selectedOrder);

			fillPreparingTable();
			fillDoneTable();
		} catch (Exception err) {
			System.out.println(err);
		}
	}

	public void setDelyvered() {
		int index = doneTable.getSelectionModel().getFocusedIndex();

		try {
			selectedOrder = doneTable.getItems().get(index);
			selectedOrder.setOrderStatus(OrderStatus.Entregue);

			OrderBO.update(selectedOrder);

			fillDoneTable();
		} catch (Exception err) {
			System.out.println(err);
		}
	}

	public void openOrderDetailsModal() {
		int indexOnHold = onHoldTable.getSelectionModel().getFocusedIndex();
		int indexOnPrep = preparingTable.getSelectionModel().getFocusedIndex();
		int indexOnDone = doneTable.getSelectionModel().getFocusedIndex();

		try {
			if (indexOnHold >= 0) {
				selectedOrder = onHoldTable.getItems().get(indexOnHold);				
			} else if (indexOnPrep >= 0) {
				selectedOrder = preparingTable.getItems().get(indexOnPrep);
			} else if (indexOnDone >= 0) {
				selectedOrder = doneTable.getItems().get(indexOnDone);
			}
			ModalLoader.load(Modal.orderDetailsModal);
			
			onHoldTable.getSelectionModel().clearSelection();
			preparingTable.getSelectionModel().clearSelection();
			doneTable.getSelectionModel().clearSelection();
		} catch (Exception err) {
			System.out.println(err);
		}
	}

	private void fillOnHoldTable() throws SQLException, ValidationException {
		ObservableList<OrderVO> orders = FXCollections.observableArrayList();
		OrderStatus status = OrderStatus.Analisando;
		allOnHoldOrders = OrderBO.findByStatus(status);

		orders.addAll(allOnHoldOrders);
		onHoldTable.setItems(orders);

		onHoldCustomer.setCellValueFactory(new PropertyValueFactory<OrderVO, CustomerVO>("customer"));

		onHoldCustomer.setCellFactory(cell -> {
			return new TableCell<OrderVO, CustomerVO>() {
				@Override
				protected void updateItem(CustomerVO item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setText("");
					} else {
						setText(item.getName());
					}
				}
			};
		} 
				);
	}

	private void fillPreparingTable() throws SQLException, ValidationException {
		ObservableList<OrderVO> orders = FXCollections.observableArrayList();
		OrderStatus status = OrderStatus.Preparando;
		allPreparingOrders = OrderBO.findByStatus(status);

		orders.addAll(allPreparingOrders);
		preparingTable.setItems(orders);

		onPrepCustomer.setCellValueFactory(new PropertyValueFactory<OrderVO, CustomerVO>("customer"));

		onPrepCustomer.setCellFactory(cell -> {
			return new TableCell<OrderVO, CustomerVO>() {
				@Override
				protected void updateItem(CustomerVO item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setText("");
					} else {
						setText(item.getName());
					}
				}
			};
		} 
				);
	}

	private void fillDoneTable() throws SQLException, ValidationException {
		ObservableList<OrderVO> orders = FXCollections.observableArrayList();
		OrderStatus status = OrderStatus.Pronto;
		allDoneOrders = OrderBO.findByStatus(status);

		orders.addAll(allDoneOrders);
		doneTable.setItems(orders);

		doneCustomer.setCellValueFactory(new PropertyValueFactory<OrderVO, CustomerVO>("customer"));

		doneCustomer.setCellFactory(cell -> {
			return new TableCell<OrderVO, CustomerVO>() {
				@Override
				protected void updateItem(CustomerVO item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setText("");
					} else {
						setText(item.getName());
					}
				}
			};
		} 
				);
	}
}
