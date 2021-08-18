package model.DAO;

import java.util.UUID;

import model.VO.CustomerVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderDAO {
    public static UUID insert(OrderVO order) {
        //Inserts Order on database.

        return UUID.randomUUID(); //To simulates order's id from database.
    }

    public static OrderVO findById(OrderVO order) {
        //Database's find method to get requested order.

        //To simulate database's return:
        return order;
    }

    public static OrderVO[] findByCustomer(CustomerVO customer) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        OrderVO order1 = new OrderVO();
        OrderVO order2 = new OrderVO();

        OrderVO orders[] = {order1, order2};

        return orders;
    }

    public static void update(OrderVO order) {
        //Updates Order on database using its id.
    }

    public static void delete(OrderVO order) {
        //Deletes Order on database using its id.
    }
}
