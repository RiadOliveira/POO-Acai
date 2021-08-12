package model.DAO;

import java.time.LocalDate;
import java.util.UUID;

import model.VO.OrderProductVO;
import model.VO.OrderVO;
import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderDAO {
    public static UUID insert(
        UUID customerId, OrderProductVO[] orderProducts, PaymentMethod paymentMethod, 
        OrderStatus status, LocalDate date, double totalPrice
    ) {
        //Inserts Order on database.

        return UUID.randomUUID(); //To simulates order's id from database.
    }

    public static OrderVO findById(UUID id) {
        //Database's find method to get requested order.

        //To simulate database's return:
        OrderVO order = new OrderVO();

        return order;
    }

    public static OrderVO[] findByCustomerId(UUID customerId) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        OrderVO order1 = new OrderVO();
        OrderVO order2 = new OrderVO();

        OrderVO orders[] = {order1, order2};

        return orders;
    }

    public static OrderVO[] findByProductId(UUID productId) {
        //Uses database's find method to get all orders of that product.

        //To simulate database's return:
        OrderVO order1 = new OrderVO();
        OrderVO order2 = new OrderVO();

        OrderVO orders[] = {order1, order2};

        return orders;
    }

    public static void update(
        UUID id, UUID customerId, OrderProductVO[] orderProducts, 
        PaymentMethod paymentMethod, OrderStatus status, LocalDate date, double totalPrice
    ) {
        //Updates Order on database.
    }

    public static void delete(UUID id) {
        //Deletes Order on database.
    }
}
