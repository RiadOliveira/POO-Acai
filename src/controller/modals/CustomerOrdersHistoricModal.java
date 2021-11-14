package controller.modals;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import controller.DashboardModal;
import controller.screens.CustomersScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.OrderBO;
import model.BO.OrderProductBO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import utils.PaymentMethod;

public class CustomerOrdersHistoricModal extends DashboardModal {
    @FXML private TableView<OrderVO> historicTable;

    @FXML private TableColumn<OrderVO, LocalDate> date;
    @FXML private TableColumn<OrderVO, PaymentMethod> paymentMethod;
    @FXML private TableColumn<OrderVO, Double> totalPrice;
    @FXML private TableColumn<OrderVO, UUID> products;

    public void initialize() {
        try {
            CustomersScreen customersScreen = new CustomersScreen();
            CustomerVO selectedCustomer = customersScreen.getSelectedCustomer();

            ObservableList<OrderVO> orders = FXCollections.observableArrayList();
            List<OrderVO> allOrders = OrderBO.findByCustomer(selectedCustomer);
    
            orders.addAll(allOrders);
            historicTable.setItems(orders);
    
            date.setCellValueFactory(new PropertyValueFactory<OrderVO, LocalDate>("date"));
            paymentMethod.setCellValueFactory(new PropertyValueFactory<OrderVO, PaymentMethod>("paymentMethod"));
            totalPrice.setCellValueFactory(new PropertyValueFactory<OrderVO, Double>("totalPrice"));
            products.setCellValueFactory(new PropertyValueFactory<OrderVO, UUID>("id"));

            date.setCellFactory(cell -> {
                return new TableCell<OrderVO, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                       super.updateItem(item, empty);
    
                       if(empty) {
                            setText("");
                       } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");    
                            setText(item.format(formatter));
                       }
                    }
                };
             } 
            );

            totalPrice.setCellFactory(cell -> {
                return new TableCell<OrderVO, Double>() {
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

            products.setCellFactory(cell -> {
                return new TableCell<OrderVO, UUID>() {
                    @Override
                    protected void updateItem(UUID item, boolean empty) {
                       super.updateItem(item, empty);
                       String productsText = "";
                        
                       try {
                            if(!empty) {
                                OrderVO order = new OrderVO();
                                order.setId(item);
    
                                List<OrderProductVO> orderProducts = OrderProductBO.findByOrder(order);
    
                                for(int ind=0 ; ind<orderProducts.size() ; ind++) {
                                    productsText = productsText.concat(orderProducts.get(ind).getProduct().getName());
                                    
                                    if(ind + 1 != orderProducts.size()) {
                                        productsText = productsText.concat(", ");
                                    }
                                }
                            }
                       } catch (Exception err) {
                            System.out.println(err.getMessage());
                       } finally {
                            setText(productsText);
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
}
