package model.DAO;

import java.time.LocalDate;

import model.VO.OrderVO;
import utils.OrderProduct;
import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderDAO {
    public static String insert(
        String customerId, OrderProduct[] orderProducts, PaymentMethod paymentMethod, 
        OrderStatus status, LocalDate date, double totalPrice
    ) {
        //Inserts order on database.

        return "orderId";
    }

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

    public static void update(
        String id, String customerId, OrderProduct[] orderProducts, 
        PaymentMethod paymentMethod, OrderStatus status, LocalDate date, double totalPrice
    ) {
        //Updates Order on database.
    }

    public static void delete(String id) {
        //Deletes Order on database.
    }
}
