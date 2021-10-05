package model.BO;

import model.DAO.OrderDAO;
import model.DAO.OrderProductDAO;
import model.DAO.ProductDAO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderProductBO {
	private static OrderDAO<OrderVO> orderDAO = new OrderDAO<OrderVO>();
	private static ProductDAO<ProductVO> productDAO = new ProductDAO<ProductVO>();
	private static OrderProductDAO<OrderProductVO> orderProductDAO = new OrderProductDAO<OrderProductVO>();

	public static boolean insert(OrderProductVO orderProduct) {
        try {
            if(orderDAO.findById(orderProduct.getOrder()) == null) {
                throw new Exception("Order not found.");
            }

            if(productDAO.findById(orderProduct.getProduct()) == null) {
                throw new Exception("Product not found.");
            }

            orderProductDAO.insert(orderProduct);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean update(OrderProductVO orderProduct) {
        try {
            if(orderProductDAO.findById(orderProduct) == null) {
                throw new Exception("OrderProduct not found.");
            }

            orderProductDAO.update(orderProduct);

            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
            
            return false;
        }
    }

    public static boolean delete(OrderProductVO orderProduct) {
        try {
            if(orderProductDAO.findById(orderProduct) == null) {
                throw new Exception("OrderProduct not found.");
            }

            orderProductDAO.delete(orderProduct);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
