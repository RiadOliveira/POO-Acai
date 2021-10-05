package model.BO;

import java.sql.ResultSet;
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
    
    public static boolean insert(OrderVO order) {
        try {
            if(customerDAO.findById(order.getCustomer()) == null) {
                throw new Exception("Requested customer does not exist.");
            }
                        
            for (OrderProductVO orderProduct : order.getOrderProducts()) {
            	order.setTotalPrice(order.getTotalPrice() + orderProduct.getQuantity() * orderProduct.getProduct().getPrice());
            }
            
            orderDAO.insert(order);
            
            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
    
    public static List<OrderVO> findByDate(List<OrderVO> allOrders, LocalDate date) {
        int searchedOrdersLength = 0;
        int searchedPositions[] = new int[allOrders.size()];

        for(int ind=0, i=0 ; ind<allOrders.size() ; ind++) {
            if(allOrders.get(ind).getDate().compareTo(date) == 0) {
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

    public static List<OrderVO> generateReport(
        List<OrderVO> allOrders, ReportType type, LocalDate date
    ) {
        switch (type) { 
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

    public static OrderVO findById(OrderVO order) {
        try {
            ResultSet findedOrderDB = orderDAO.findById(order);
    
            CustomerVO customer = new CustomerVO();
            customer.setId(UUID.fromString(findedOrderDB.getString("customer_id")));
            customer = CustomerBO.findById(customer);
            
            PaymentMethod[] paymentMethod = PaymentMethod.values();
            OrderStatus[] orderStatus = OrderStatus.values();
            
            OrderVO findedOrder = new OrderVO();
            findedOrder.setId(UUID.fromString(findedOrderDB.getString("id")));
            findedOrder.setCustomer(customer);
            findedOrder.setPaymentMethod(paymentMethod[findedOrderDB.getInt("payment_method")]);
            findedOrder.setOrderStatus(orderStatus[findedOrderDB.getInt("status")]);
            findedOrder.setDate(findedOrderDB.getDate("order_date").toLocalDate());
    
            return findedOrder;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return null;
        }
    }

    public static List<OrderVO> findAll() {
        try {
            ResultSet findedOrdersDB = orderDAO.findAll();
            List<OrderVO> findedOrders = new ArrayList<OrderVO>();
    
            PaymentMethod[] paymentMethod = PaymentMethod.values();
            OrderStatus[] orderStatus = OrderStatus.values();
    
            while(findedOrdersDB.next()) {
                OrderVO order = new OrderVO();
                
                order.setId(UUID.fromString(findedOrdersDB.getString("id")));
                order.setPaymentMethod(paymentMethod[findedOrdersDB.getInt("payment_method")]);
                order.setOrderStatus(orderStatus[findedOrdersDB.getInt("status")]);
                order.setTotalPrice(findedOrdersDB.getDouble("total_price"));
                order.setDate(findedOrdersDB.getDate("order_date").toLocalDate());
                
                CustomerVO customer = new CustomerVO();
                customer.setId(UUID.fromString(findedOrdersDB.getString("customer_id")));
                customer = CustomerBO.findById(customer);
                
                order.setCustomer(customer);
    
                findedOrders.add(order);
            }
    
            return findedOrders;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return null;
        }
    }

    public static boolean update(OrderVO order) {
        try {
            if(orderDAO.findById(order) == null) {
                throw new Exception("Order not found.");
            }

            if(customerDAO.findById(order.getCustomer()) == null) {
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

    public static boolean delete(OrderVO order) {
        try {
            if(orderDAO.findById(order) == null) {
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
