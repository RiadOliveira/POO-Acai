package model.BO;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

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

    public static OrderProductVO findById(OrderProductVO orderProduct) {
        try {
            ResultSet findedOrderProductDB = orderProductDAO.findById(orderProduct);

            OrderVO order = new OrderVO();
            order.setId(UUID.fromString(findedOrderProductDB.getString("order_id")));
            order = OrderBO.findById(order);
            
            ProductVO product = new ProductVO();
            product.setId(UUID.fromString(findedOrderProductDB.getString("product_id")));
            product = ProductBO.findById(product);
            
            OrderProductVO findedOrderProduct = new OrderProductVO();
            findedOrderProduct.setId(UUID.fromString(findedOrderProductDB.getString("id")));
            findedOrderProduct.setOrder(order);
            findedOrderProduct.setProduct(product);
            findedOrderProduct.setQuantity(findedOrderProductDB.getInt("quantity"));

            return findedOrderProduct;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
            
            return null;
        }
    }

    public static List<OrderProductVO> findByOrder(OrderVO order) {
        try {
            ResultSet findedOrderProductsDB = orderProductDAO.findByOrder(order);
            List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

            while(findedOrderProductsDB.next()) {
                ProductVO product = new ProductVO();
                product.setId(UUID.fromString(findedOrderProductsDB.getString("product_id")));
                product = ProductBO.findById(product);
                
                OrderProductVO findedOrderProduct = new OrderProductVO();
                findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
                findedOrderProduct.setOrder(order);
                findedOrderProduct.setProduct(product);
                findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));
    
                findedOrderProducts.add(findedOrderProduct);
            }

            return findedOrderProducts;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
            
            return null;
        }
    }

    public static List<OrderProductVO> findByProduct(ProductVO product) {
        try {
            ResultSet findedOrderProductsDB = orderProductDAO.findByProduct(product);
            List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

            while(findedOrderProductsDB.next()) {
                OrderVO order = new OrderVO();
                order.setId(UUID.fromString(findedOrderProductsDB.getString("order_id")));
                order = OrderBO.findById(order);
                
                OrderProductVO findedOrderProduct = new OrderProductVO();
                findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
                findedOrderProduct.setOrder(order);
                findedOrderProduct.setProduct(product);
                findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));
    
                findedOrderProducts.add(findedOrderProduct);
            }

            return findedOrderProducts;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
            
            return null;
        }
    }

    public static List<OrderProductVO> findAll() {
        try {
            ResultSet findedOrderProductsDB = orderProductDAO.findAll();
            List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

            while(findedOrderProductsDB.next()) {
                OrderVO order = new OrderVO();
                order.setId(UUID.fromString(findedOrderProductsDB.getString("order_id")));
                order = OrderBO.findById(order);
                
                ProductVO product = new ProductVO();
                product.setId(UUID.fromString(findedOrderProductsDB.getString("product_id")));
                product = ProductBO.findById(product);
                
                OrderProductVO findedOrderProduct = new OrderProductVO();
                findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
                findedOrderProduct.setOrder(order);
                findedOrderProduct.setProduct(product);
                findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));
    
                findedOrderProducts.add(findedOrderProduct);
            }

            return findedOrderProducts;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
            
            return null;
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
