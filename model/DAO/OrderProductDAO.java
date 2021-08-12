package model.DAO;

import java.util.UUID;

import model.VO.OrderProductVO;

public class OrderProductDAO {
    public static UUID insert(UUID orderId, UUID productId, int quantity) {
        //Inserts OrderProduct on database.

        return UUID.randomUUID(); //To simulates orderProduct's id from database.
    }

    public static OrderProductVO findById(UUID id) {
        //Database's find method to get requested orderProduct.

        //To simulate database's return:
        OrderProductVO orderProduct = new OrderProductVO();

        return orderProduct;
    }

    public static OrderProductVO[] findByCustomerId(UUID customerId) {
        //Uses database's find method to get all orderProducts of that customer.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }

    public static OrderProductVO[] findByProductId(UUID productId) {
        //Uses database's find method to get all orderProducts of that product.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }

    public static void updateQuantity(UUID id, int quantity) {
        //Updates OrderProduct quantity on database.
    }

    public static void delete(UUID id) {
        //Deletes OrderProduct on database.
    }
}
