package model.DAO;

import java.util.UUID;

import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderProductDAO {
    public static UUID insert(OrderProductVO orderProduct) { //Maybe return orderProduct with id.
        //Inserts OrderProduct on database.

        return UUID.randomUUID(); //To simulates orderProduct's id from database.
    }

    public static OrderProductVO findById(OrderProductVO orderProduct) {
        //Database's find method to get requested orderProduct.
        
        return orderProduct;
    }

    public static OrderProductVO[] findByProduct(ProductVO product) {
        //Uses database's find method to get all orderProducts of that product.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }
    
    public static OrderProductVO[] findByOrder(OrderVO order) {
    	//Uses database's find method to get all orderProducts of that order.

        //To simulate database's return:
        OrderProductVO orderProduct1 = new OrderProductVO();
        OrderProductVO orderProduct2 = new OrderProductVO();

        OrderProductVO orders[] = {orderProduct1, orderProduct2};

        return orders;
    }

    public static void update(OrderProductVO orderProduct) {
        //Updates OrderProduct quantity on database using its id.
    }

    public static void delete(OrderProductVO orderProduct) {
        //Deletes OrderProduct on database using its id.
    }
}
