package model.BO;

import java.util.UUID;

import model.DAO.OrderDAO;
import model.DAO.OrderProductDAO;
import model.DAO.ProductDAO;
import model.VO.OrderProductVO;

public class OrderProductBO {
    public static boolean create(OrderProductVO orderProduct) {
        try {
            if(OrderDAO.findById(orderProduct.getOrder()) == null) {
                throw new Exception("Order not found.");
            }

            if(ProductDAO.findById(orderProduct.getProduct()) == null) {
                throw new Exception("Product not found.");
            }

            UUID orderProductId = OrderProductDAO.insert(orderProduct);

            orderProduct.setId(orderProductId);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean update(OrderProductVO orderProduct) {
        try {
            if(OrderProductDAO.findById(orderProduct) == null) {
                throw new Exception("OrderProduct not found.");
            }

            OrderProductDAO.update(orderProduct);

            return true;
        } catch (Exception err) {
            //Handle exception.
            
            return false;
        }
    }

    public static boolean delete(OrderProductVO orderProduct) {
        try {
            if(OrderProductDAO.findById(orderProduct) == null) {
                throw new Exception("OrderProduct not found.");
            }

            OrderProductDAO.delete(orderProduct);
            orderProduct = null;

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }
}
