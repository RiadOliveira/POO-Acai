package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderProductDAO extends BaseDAO {
    public static void insert(OrderProductVO orderProduct) throws SQLException {
    	Connection connection= getConnection();
    	String query = "INSERT INTO order_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setObject(1, orderProduct.getOrder().getId());
    	statement.setObject(2, orderProduct.getProduct().getId());
    	statement.setInt(3, orderProduct.getQuantity());
    	
    	statement.execute();
    }

    public static OrderProductVO findById(OrderProductVO orderProduct) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM order_products WHERE id=?::uuid";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, orderProduct.getId().toString());
        
        ResultSet findedOrderProducts = statement.executeQuery();
        
        if (!findedOrderProducts.next()) {
        	return null;
        }        
        
        OrderVO order = new OrderVO();
        order.setId(UUID.fromString(findedOrderProducts.getString("order_id")));
        order = OrderDAO.findById(order);
        
        ProductVO product = new ProductVO();
        product.setId(UUID.fromString(findedOrderProducts.getString("product_id")));
        product = ProductDAO.findById(product);
        
        OrderProductVO orderProductVO = new OrderProductVO();
        orderProductVO.setId(UUID.fromString(findedOrderProducts.getString("id")));
        orderProductVO.setOrder(order);
        orderProductVO.setProduct(product);
        orderProductVO.setQuantity(findedOrderProducts.getInt("quantity"));
        
        return orderProductVO;
    }

    public static List<OrderProductVO> findByProduct(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	String query = "SELECT * FROM order_products WHERE product_id=?::uuid";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setString(1, product.getId().toString());
    	
    	ResultSet findedOrderProducts = statement.executeQuery();
    	List<OrderProductVO> orderProducts = new ArrayList<OrderProductVO>();
    	
    	if (!findedOrderProducts.next()) {
    		return null;
    	}
            	
        while(findedOrderProducts.next()) {
        	OrderVO order = new OrderVO();
            order.setId(UUID.fromString(findedOrderProducts.getString("order_id")));
            order = OrderDAO.findById(order);
            
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setId(UUID.fromString(findedOrderProducts.getString("id")));
            orderProductVO.setOrder(order);
            orderProductVO.setProduct(product);
            orderProductVO.setQuantity(findedOrderProducts.getInt("quantity"));
            
            orderProducts.add(orderProductVO);
        }
        
        return orderProducts;
    }
    
    public static List<OrderProductVO> findByOrder(OrderVO order) throws SQLException {
    	Connection connection = getConnection();
    	String query = "SELECT * FROM order_products WHERE order_id=?::uuid";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setString(1, order.getId().toString());
    	
    	ResultSet findedOrderProducts = statement.executeQuery();
    	List<OrderProductVO> orderProducts = new ArrayList<OrderProductVO>();
    	
    	if (!findedOrderProducts.next()) {
    		return null;
    	}
            	
        while(findedOrderProducts.next()) {
        	ProductVO product = new ProductVO();
            product.setId(UUID.fromString(findedOrderProducts.getString("product_id")));
            product = ProductDAO.findById(product);
            
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setId(UUID.fromString(findedOrderProducts.getString("id")));
            orderProductVO.setOrder(order);
            orderProductVO.setProduct(product);
            orderProductVO.setQuantity(findedOrderProducts.getInt("quantity"));
            
            orderProducts.add(orderProductVO);
        }
        
        return orderProducts;
    }

    public static void update(OrderProductVO orderProduct) throws SQLException {
    	Connection connection = getConnection();

        String query = "UPDATE order_products SET order_id=?, product_id=?, quantity=? WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setObject(1, orderProduct.getOrder().getId());
		statement.setObject(2, orderProduct.getProduct().getId());
		statement.setInt(3, orderProduct.getQuantity());
		statement.setObject(4, orderProduct.getId());

        statement.execute();
    }

    public static void delete(OrderProductVO orderProduct) throws SQLException {
    	Connection connection = getConnection();

        String query = "DELETE FROM order_products WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, orderProduct.getId().toString());

        statement.execute();
    }
}
