package model.BO;

import java.util.UUID;

import model.DAO.OrderDAO;
import model.DAO.OrderProductDAO;
import model.DAO.ProductDAO;
import model.VO.OrderProductVO;

public class OrderProductBO {
    public static boolean create(
        OrderProductVO orderProduct, UUID orderId, 
        UUID productId, int quantity
    ) {
        try {
            if(OrderDAO.findById(orderId) == null) {
                throw new Exception("Order not found.");
            }

            if(ProductDAO.findById(productId) == null) {
                throw new Exception("Product not found.");
            }

            UUID orderProductId = OrderProductDAO.insert(orderId, productId, quantity);

            orderProduct.setId(orderProductId);
            orderProduct.setOrderId(orderId);
            orderProduct.setProductId(productId);
            orderProduct.setQuantity(quantity);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean updateQuantity(OrderProductVO orderProduct, int quantity) {
        try {
            if(OrderProductDAO.findById(orderProduct.getId()) == null) {
                throw new Exception("OrderProduct not found.");
            }

            OrderProductDAO.updateQuantity(orderProduct.getId(), quantity);

            orderProduct.setQuantity(quantity);

            return true;
        } catch (Exception err) {
            //Handle exception.
            
            return false;
        }
    }

    public static boolean delete(OrderProductVO orderProduct) {
        try {
            if(OrderProductDAO.findById(orderProduct.getId()) == null) {
                throw new Exception("OrderProduct not found.");
            }

            OrderProductDAO.delete(orderProduct.getId());
            orderProduct = null;

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }
}
