package controller.modals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.DashboardModal;
import controller.screens.SalesScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.util.StringConverter;
import model.BO.CustomerBO;
import model.BO.OrderBO;
import model.BO.OrderProductBO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import utils.OrderStatus;
import utils.PaymentMethod;
import utils.Screen;
import view.ModalLoader;
import view.ScreenLoader;

public class FinishSaleModal extends DashboardModal {
	@FXML private ComboBox<CustomerVO> customersBox;
	@FXML private ComboBox<PaymentMethod> paymentMethodBox;
	
	private ObservableList<String> customersList = FXCollections.observableArrayList();
	
	private OrderVO order = new OrderVO();
	private ObservableList<OrderProductVO> selectedProductsList = FXCollections.observableArrayList();
	
	public void initialize() {
		ObservableList<CustomerVO> customers = FXCollections.observableArrayList();
		ObservableList<PaymentMethod> paymentMethods = FXCollections.observableArrayList();
		
		try {
			customers.addAll(CustomerBO.findAll());
			paymentMethods.addAll(PaymentMethod.values());
			
			customers.forEach(customer -> {
				customersList.add(customer.getName());
			});
			
			StringConverter<CustomerVO> converter = new StringConverter<CustomerVO>() {
				@Override
				public String toString(CustomerVO object) {
					return object.getName();
				}
	
				@Override
				public CustomerVO fromString(String string) {
					return null;
				}
			};

			customersBox.setConverter(converter);

			customersBox.setItems(customers);
			customersBox.setCellFactory(cell -> {
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
			
			paymentMethodBox.setItems(paymentMethods);
		} catch (Exception err) {
			System.out.println(err);
		}
    }
	
	public void finishCheckout() {
		try {
			order.setCustomer(customersBox.getValue());
			order.setPaymentMethod(paymentMethodBox.getValue());
			order.setOrderStatus(OrderStatus.Analisando);
			order.setDate(LocalDate.now());
			
			OrderBO.insert(order);
			
			SalesScreen salesScreen = new SalesScreen();
			selectedProductsList = salesScreen.getSelectedProductsList();
			
			selectedProductsList.forEach(product -> {
				OrderProductVO orderProduct = new OrderProductVO();
				try {
					orderProduct = product;
					orderProduct.setOrder(order);
					OrderProductBO.insert(orderProduct);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception err) {
			System.out.println(err);
		}
		
		ScreenLoader.load(Screen.salesScreen);
		ModalLoader.close();
	}
}
