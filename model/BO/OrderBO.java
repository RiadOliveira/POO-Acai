package model.BO;

import java.time.LocalDate;

import model.VO.OrderVO;
import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportType;
import utils.OrderProduct;

public class OrderBO {
    public static boolean create(
        OrderVO order, String customerId, OrderProduct[] orderProducts, 
        PaymentMethod paymentMethod, LocalDate date, double totalPrice
    ) {
        try {
            if(UserBO.findById(customerId) == null) {
                throw new Exception("Requested customer does not exist.");
            }

            //Insert order's data into database.

            //Needs to insert id of Order.
            order.setCustomerId(customerId);
            order.setOrderProducts(orderProducts);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(OrderStatus.analyzing);
            order.setDate(date);
            order.setTotalPrice(totalPrice);

            return true;
        } catch(Exception err) {
            //Handle the exception.

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
        OrderVO order, String customerId, OrderProduct[] orderProducts, 
        PaymentMethod paymentMethod, OrderStatus status, LocalDate date, double totalPrice
    ) {
        try {
            if(UserBO.findById(customerId) == null) {
                throw new Exception("Requested customer does not exist.");
            }

            //Update order on database.

            order.setCustomerId(customerId);
            order.setOrderProducts(orderProducts);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(status);
            order.setDate(date);
            order.setTotalPrice(totalPrice);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean delete(OrderVO order) { //Verify if can pass only order's id.
        try {
            //Delete order on database.

            //After that, on main class, needs to delete this object on order's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}