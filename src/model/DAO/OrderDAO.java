package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.CustomerVO;
import model.VO.OrderVO;

public class OrderDAO<VO extends OrderVO> extends BaseDAO<VO> {
    public void insert(VO order) throws SQLException {
    	Connection connection = getConnection();
		String query = "INSERT INTO orders (customer_id, payment_method, status, total_price, order_date, order_time) values (?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		statement.setObject(1, order.getCustomer().getId());
		statement.setInt(2, order.getPaymentMethod().ordinal());
		statement.setInt(3, order.getOrderStatus().ordinal());
		statement.setDouble(4, order.getTotalPrice());
		statement.setDate(5, Date.valueOf(order.getDate()));
		statement.setTime(6, Time.valueOf(order.getTime()));

		statement.execute();
		
		ResultSet generatedKeys = statement.getGeneratedKeys();

        if(generatedKeys.next()) {
            order.setId(UUID.fromString(generatedKeys.getString(1)));
        } else {
            throw new SQLException("Order's ID not found on database");
        }
    }

    public ResultSet findAll() throws SQLException {
    	Connection connection = getConnection();
		String query = "SELECT * FROM orders";
		
		Statement statement;
        ResultSet findedOrders;

        statement = connection.createStatement();

        findedOrders = statement.executeQuery(query);

        return findedOrders;
    }
    
    public ResultSet findById(VO order) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "SELECT * FROM orders WHERE id=?::uuid";
    	
    	PreparedStatement statement;
    	
    	ResultSet findedOrder = null;
    	
		statement = connection.prepareStatement(query);
		statement.setString(1, order.getId().toString());
		
		findedOrder = statement.executeQuery();
		
		if(!findedOrder.next()) {
			return null;
		}
    		
    	return findedOrder;
    }

    public ResultSet findByCustomer(CustomerVO customer) throws SQLException {
    	Connection connection = getConnection();
		String query = "SELECT * FROM orders WHERE customer_id=?::uuid";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, customer.getId().toString());
		
        ResultSet findedOrders;
        findedOrders = statement.executeQuery(query);
        
        if(!findedOrders.next()) {
        	return null;
        }

        return findedOrders;
    }

    public void update(VO order) throws SQLException {
    	Connection connection = getConnection();

        String query = "UPDATE orders SET customer_id=?, payment_method=?, status=?, total_price=?, order_date=? WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setObject(1, order.getCustomer().getId());
		statement.setInt(2, order.getPaymentMethod().ordinal());
		statement.setInt(3, order.getOrderStatus().ordinal());
		statement.setDouble(4, order.getTotalPrice());
		statement.setDate(5, Date.valueOf(order.getDate()));
		statement.setObject(6, order.getId());

        statement.execute();
    }

    public void delete(VO order) throws SQLException {
    	Connection connection = getConnection();

        String query = "DELETE FROM orders WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, order.getId().toString());

        statement.execute();
    }
}
