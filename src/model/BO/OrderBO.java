package model.BO;

import java.time.LocalDate;

import model.DAO.CustomerDAO;
import model.DAO.OrderDAO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import utils.ReportType;

public class OrderBO {
    public static boolean create(OrderVO order) {
        try {
            if(CustomerDAO.findById(order.getCustomer()) == null) {
                throw new Exception("Requested customer does not exist.");
            }
                        
            for (OrderProductVO orderProduct : order.getOrderProducts()) {
            	order.setTotalPrice(orderProduct.getQuantity() * orderProduct.getProduct().getPrice());
            }
            
            OrderDAO.insert(order);
            
            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
    
    public static OrderVO[] findByDate(OrderVO[] allOrders, LocalDate date) {
        int searchedOrdersLength = 0;
        int searchedPositions[] = new int[allOrders.length];

        for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
            if(allOrders[ind].getDate().compareTo(date) == 0) {
                searchedOrdersLength++;
                searchedPositions[i++] = ind;
            }
        }

        OrderVO searchedOrders[] = new OrderVO[searchedOrdersLength];

        for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
            searchedOrders[ind] = allOrders[searchedPositions[ind]];
        }

        return searchedOrders;
    }

    public static OrderVO[] generateReport(
        OrderVO[] allOrders, ReportType type, LocalDate date
    ) {
        switch(type) { 
            case day: return OrderBO.findByDate(allOrders, date);
            
            case week: {
                int startOfWeek = date.getDayOfMonth() - date.getDayOfWeek().getValue();

                LocalDate initialDate = LocalDate.of(date.getYear(), date.getMonthValue(), startOfWeek);
                LocalDate finalDate = initialDate.plusDays(7);

                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.length];

                for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
                    if(
                        allOrders[ind].getDate().compareTo(initialDate) >= 0 &&
                        allOrders[ind].getDate().compareTo(finalDate) < 0
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                OrderVO searchedOrders[] = new OrderVO[searchedOrdersLength];

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders[ind] = allOrders[searchedPositions[ind]];
                }

                return searchedOrders;
            }
            
            case month: {
                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.length];

                for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
                    if(
                        allOrders[ind].getDate().getYear() == date.getYear() &&
                        allOrders[ind].getDate().getMonthValue() == date.getMonthValue()
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                OrderVO searchedOrders[] = new OrderVO[searchedOrdersLength];

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders[ind] = allOrders[searchedPositions[ind]];
                }

                return searchedOrders;
            }

            default: return null;
        }
    }

    public static boolean update(OrderVO order) {
        try {
            if(OrderDAO.findById(order) == null) {
                throw new Exception("Order not found.");
            }

            if(CustomerDAO.findById(order.getCustomer()) == null) {
                throw new Exception("Customer not found.");
            }

            OrderDAO.update(order);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(OrderVO order) {
        try {
            if(OrderDAO.findById(order) == null) {
                throw new Exception("Order not found.");
            }

            OrderDAO.delete(order);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
