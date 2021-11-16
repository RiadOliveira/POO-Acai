package controller.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
import utils.OrderStatus;

public class HomeScreen extends DashboardPagesRedirect {
	@FXML private TableView<OrderVO> onHoldTable;
	@FXML private TableView<ProductVO> preparingTable;
	@FXML private TableView<ProductVO> doneTable;

	@FXML private TableColumn<OrderVO, CustomerVO> onHoldCustomer;
	@FXML private TableColumn<ProductVO, String> onPrepCustomer;
	@FXML private TableColumn<ProductVO, String> doneCustomer;
	
	private static UserVO selectedEmployee = null;
	private List<OrderVO> allOrders = null;
	
	private ObservableList<CustomerVO> onHoldCustomers = FXCollections.observableArrayList();
	private ObservableList<CustomerVO> onPrepCustomers = FXCollections.observableArrayList();
	private ObservableList<CustomerVO> doneCustomers = FXCollections.observableArrayList();
	
	public void initialize() {
		if(selectedEmployee != null) {
            selectedEmployee = null;
        }
		
		try {
			fillOnHoldTable();
		} catch (SQLException | ValidationException err) {
			System.out.println(err.getMessage());
		}
	}
	
	private void fillOnHoldTable() throws SQLException, ValidationException {
		ObservableList<OrderVO> orders = FXCollections.observableArrayList();
		OrderStatus status = OrderStatus.Analisando;
		allOrders = OrderBO.findByStatus(status);

        orders.addAll(allOrders);
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
}
