package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import errors.ValidationException;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderProductDAO<VO extends OrderProductVO> extends BaseDAO<VO> {
	public void insert(VO orderProduct) throws SQLException, ValidationException {
    	Connection connection= getConnection();
    	String query = "INSERT INTO order_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
    	
    	PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    	statement.setObject(1, orderProduct.getOrder().getId());
    	statement.setObject(2, orderProduct.getProduct().getId());
    	statement.setInt(3, orderProduct.getQuantity());
    	
    	statement.execute();
    	
    	ResultSet generatedKeys = statement.getGeneratedKeys();
    	
    	if (generatedKeys.next()) {
    		orderProduct.setId(UUID.fromString(generatedKeys.getString(1)));
    	} else {
    		throw new SQLException("OrderProduct's ID not found on database");
    	}
    }
	
	public ResultSet findAll() throws SQLException {
		Connection connection = getConnection();
		String query = "SELECT * FROM order_products";
		
		Statement statement = connection.createStatement();
        ResultSet findedOrders = statement.executeQuery(query);
  
        return findedOrders;
	}

    public ResultSet findById(VO orderProduct) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM order_products WHERE id=?::uuid";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, orderProduct.getId().toString());
        
        ResultSet findedOrderProducts = statement.executeQuery();
        
        if (!findedOrderProducts.next()) {
        	return null;
        }        
    
        return findedOrderProducts;
    }

    public ResultSet findByProduct(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	String query = "SELECT * FROM order_products WHERE product_id=?::uuid";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setString(1, product.getId().toString());
    	
    	ResultSet findedOrderProducts = statement.executeQuery();
        
        return findedOrderProducts;
    }
    
    public ResultSet findByOrder(OrderVO order) throws SQLException {
    	Connection connection = getConnection();
    	String query = "SELECT * FROM order_products WHERE order_id=?::uuid";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setString(1, order.getId().toString());
    	
    	ResultSet findedOrderProducts = statement.executeQuery();
        
        return findedOrderProducts;
    }

    public void update(VO orderProduct) throws SQLException {
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

    public void delete(VO orderProduct) throws SQLException {
    	Connection connection = getConnection();

        String query = "DELETE FROM order_products WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, orderProduct.getId().toString());

        statement.execute();
    }
}
