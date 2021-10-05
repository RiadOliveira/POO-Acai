package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import model.DAO.CustomerDAO;
import model.DAO.OrderDAO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.CustomerVO;
import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportType;

public class OrderBO {
    private static CustomerDAO<CustomerVO> customerDAO = new CustomerDAO<CustomerVO>();
    private static OrderDAO<OrderVO> orderDAO = new OrderDAO<OrderVO>();
    
    public static boolean insert(OrderVO order) throws SQLException {
    	ResultSet findedCustomer = customerDAO.findById(order.getCustomer());
    	
        try {
            if(findedCustomer.next()) {
                throw new Exception("Requested customer does not exist.");
            }
                        
            for (OrderProductVO orderProduct : order.getOrderProducts()) {
            	order.setTotalPrice(orderProduct.getQuantity() * orderProduct.getProduct().getPrice());
            }
            
            orderDAO.insert(order);
            
            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
    
    public static List<OrderVO> findByDate(List<OrderVO> allOrders, LocalDate date) throws SQLException {
    	ResultSet findedOrders = orderDAO.findAll();
    	
    	List<OrderVO> orders = new ArrayList<OrderVO>();
    	
    	PaymentMethod[] paymentMethod = PaymentMethod.values();
    	OrderStatus[] orderStatus = OrderStatus.values();

    	while(findedOrders.next()) {
    		if (findedOrders.getDate("order_date").toLocalDate() == date) {
    			OrderVO order = new OrderVO();
    			
    			order.setId(UUID.fromString(findedOrders.getString("id")));
    			order.setPaymentMethod(paymentMethod[findedOrders.getInt("payment_method")]);
    			order.setOrderStatus(orderStatus[findedOrders.getInt("status")]);
    			order.setTotalPrice(findedOrders.getDouble("total_price"));
    			order.setDate(findedOrders.getDate("order_date").toLocalDate());
    			
    			CustomerVO customer = new CustomerVO();
    			customer.setId(UUID.fromString(findedOrders.getString("customer_id")));
    			
    			CustomerDAO<CustomerVO> customerDAO = new CustomerDAO<CustomerVO>();
    			ResultSet findedCustomer = customerDAO.findById(customer);
    			customer.setId(UUID.fromString(findedCustomer.getString("id")));
    			customer.setName(findedCustomer.getString("name"));
    			customer.setCpf(findedCustomer.getString("cpf"));
    			customer.setPhoneNumber(findedCustomer.getString("phone_number"));
    			customer.setAddress(findedCustomer.getString("address")); 
    			
    			order.setCustomer(customer);
    			
    			orders.add(order);
    		}
    	}
    	
        return orders;
    }

    public static List<OrderVO> generateReport(
        List<OrderVO> allOrders, ReportType type, LocalDate date
    ) throws SQLException {
        switch(type) { 
            case day: return OrderBO.findByDate(allOrders, date);
            
            case week: {
                int startOfWeek = date.getDayOfMonth() - date.getDayOfWeek().getValue();

                LocalDate initialDate = LocalDate.of(date.getYear(), date.getMonthValue(), startOfWeek);
                LocalDate finalDate = initialDate.plusDays(7);

                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.size()];

                for(int ind=0, i=0 ; ind<allOrders.size() ; ind++) {
                    if(
                        allOrders.get(ind).getDate().compareTo(initialDate) >= 0 &&
                        allOrders.get(ind).getDate().compareTo(finalDate) < 0
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                List<OrderVO> searchedOrders = new ArrayList<OrderVO>();

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders.add(allOrders.get(searchedPositions[ind]));
                }

                return searchedOrders;
            }
            
            case month: {
                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.size()];

                for(int ind=0, i=0 ; ind < allOrders.size() ; ind++) {
                    if(
                        allOrders.get(ind).getDate().getYear() == date.getYear() &&
                        allOrders.get(ind).getDate().getMonthValue() == date.getMonthValue()
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                List<OrderVO> searchedOrders = new ArrayList<OrderVO>();

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders.add(allOrders.get(searchedPositions[ind]));
                }

                return searchedOrders;
            }

            default: return null;
        }
    }

    public static boolean update(OrderVO order) throws SQLException {
    	ResultSet findedCustomer = customerDAO.findById(order.getCustomer());
    	ResultSet findedOrder = orderDAO.findById(order);
        try {
            if(!findedOrder.next()) {
                throw new Exception("Order not found.");
            }

            if(!findedCustomer.next()) {
                throw new Exception("Customer not found.");
            }

            orderDAO.update(order);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(OrderVO order) throws SQLException {
    	ResultSet orderResult = orderDAO.findById(order);
        try {
            if(!orderResult.next()) {
                throw new Exception("Order not found.");
            }

            orderDAO.delete(order);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
