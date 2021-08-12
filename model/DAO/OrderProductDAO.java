package model.DAO;

import model.VO.OrderProductVO;

public class OrderProductDAO {
    public static String insert(String orderId, String productId, int quantity) {
        //Inserts OrderProduct on database.

        return "orderProductId";
    }

    public static OrderProductVO findById(String id) {
        //Database's find method to get requested orderProduct.

        //To simulate database's return:
        OrderProductVO orderProduct = new OrderProductVO();

        return orderProduct;
    }

    public static OrderProductVO[] findByCustomerId(String customerId) {
        //Uses database's find method to get all orderProducts of that customer.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }

    public static OrderProductVO[] findByProductId(String productId) {
        //Uses database's find method to get all orderProducts of that product.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }

    public static void updateQuantity(String id, int quantity) {
        //Updates OrderProduct quantity on database.
    }

    public static void delete(String id) {
        //Deletes OrderProduct on database.
    }
}
