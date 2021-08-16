package model.BO;

import java.time.LocalDate;
import java.util.UUID;

import model.DAO.OrderDAO;
import model.DAO.UserDAO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportType;

public class OrderBO {
    public static boolean create(
        OrderVO order, UUID customerId, OrderProductVO[] orderProducts, 
        PaymentMethod paymentMethod, LocalDate date, double totalPrice
    ) {
        try {
            if(UserDAO.findById(customerId) == null) {
                throw new Exception("Requested customer does not exist.");
            }

            UUID orderId = OrderDAO.insert(
                customerId, orderProducts, paymentMethod, 
                OrderStatus.analyzing, date, totalPrice
            );

            order.setId(orderId);
            order.setCustomerId(customerId);
            order.setOrderProducts(orderProducts);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(OrderStatus.analyzing);
            order.setDate(date);
            order.setTotalPrice(totalPrice);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }
    
    public static OrderVO[] findByDate(OrderVO[] allOrders, int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);

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
        OrderVO allOrders[], ReportType type, 
        int day, int month, int year
    ) {
        switch(type) { 
            case day: return OrderBO.findByDate(allOrders, day, month, year);
            
            case week: {
                int startOfWeek = day - LocalDate.of(year, month, day).getDayOfWeek().getValue();

                LocalDate initialDate = LocalDate.of(year, month, startOfWeek);
                LocalDate finalDate = LocalDate.of(year, month, startOfWeek).plusDays(7);

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
                        allOrders[ind].getDate().getYear() == year &&
                        allOrders[ind].getDate().getMonthValue() == month
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

    public static boolean update(
        OrderVO order, UUID customerId, OrderProductVO[] orderProducts, 
        PaymentMethod paymentMethod, OrderStatus status, LocalDate date, double totalPrice
    ) {
        try {
            if(OrderDAO.findById(order.getId()) == null) {
                throw new Exception("Order not found.");
            }

            if(UserDAO.findById(customerId) == null) {
                throw new Exception("Customer not found.");
            }

            OrderDAO.update(
                order.getId(), customerId, orderProducts, paymentMethod,
                status, date, totalPrice
            );

            order.setCustomerId(customerId);
            order.setOrderProducts(orderProducts);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(status);
            order.setDate(date);
            order.setTotalPrice(totalPrice);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean delete(OrderVO order) {
        try {
            if(OrderDAO.findById(order.getId()) == null) {
                throw new Exception("Order not found.");
            }

            OrderDAO.delete(order.getId());
            order = null;

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }
}
