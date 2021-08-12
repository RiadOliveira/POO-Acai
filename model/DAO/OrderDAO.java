package model.DAO;

import java.time.LocalDate;

import model.VO.OrderVO;
import utils.OrderProduct;
import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderDAO {
    public static OrderVO findById(String id) {
        //Database's find method to get requested order.

        //To simulate database's return:
        OrderVO order = new OrderVO();

        return order;
    }

    public static OrderVO[] findByCustomerId(String customerId) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        OrderVO order1 = new OrderVO();
        OrderVO order2 = new OrderVO();

        OrderVO orders[] = {order1, order2};

        return orders;
    }

    public static OrderVO[] findByProductId(String productId) {
        //Uses database's find method to get all orders of that product.

        //To simulate database's return:
        OrderVO order1 = new OrderVO();
        OrderVO order2 = new OrderVO();

        OrderVO orders[] = {order1, order2};

        return orders;
    }

    public static boolean update(
        String id, String customerId, OrderProduct[] orderProducts, 
        PaymentMethod paymentMethod, OrderStatus status, LocalDate date, double totalPrice
    ) {
        try {
            OrderDAO.findById(id);

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
}
