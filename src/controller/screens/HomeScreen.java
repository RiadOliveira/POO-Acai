package controller.screens;

import java.sql.SQLException;
import java.util.List;

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
import model.VO.CustomerVO;
import model.VO.OrderVO;
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

	private static OrderVO selectedOrder= null;
	private List<OrderVO> allOnHoldOrders = null;
	private List<OrderVO> allPreparingOrders = null;
	private List<OrderVO> allDoneOrders = null;

	public void initialize() {
		try {
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
		int index = onHoldTable.getSelectionModel().getFocusedIndex();

		try {
			selectedOrder = onHoldTable.getItems().get(index);
			System.out.println(selectedOrder);
			ModalLoader.load(Modal.customerOrdersHistoricModal);
		} catch (Exception err) {
			errorMessage.setStyle(errorMessage.getStyle() + "-fx-opacity: 1;");
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
