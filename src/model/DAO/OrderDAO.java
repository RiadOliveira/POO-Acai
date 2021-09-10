package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.CustomerVO;
import model.VO.OrderVO;
import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderDAO extends BaseDAO {
    public static void insert(OrderVO order) throws SQLException {
    	Connection connection = getConnection();
		String query = "INSERT INTO orders (customer_id, payment_method, status, total_price, order_date) values (?, ?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setObject(1, order.getCustomer().getId());
		statement.setInt(2, order.getPaymentMethod().ordinal());
		statement.setInt(3, order.getOrderStatus().ordinal());
		statement.setDouble(4, order.getTotalPrice());
		statement.setDate(5, Date.valueOf(order.getDate()));

		statement.execute();
    }

    public static List<OrderVO> findAll() throws SQLException {
    	Connection connection = getConnection();
		String query = "SELECT * FROM orders";
		
//		 LEFT JOIN customers ON orders.customer_id = customers.id
		
		Statement statement;
        ResultSet findedOrders;
        List<OrderVO> orders = new ArrayList<OrderVO>();

        statement = connection.createStatement();

        findedOrders = statement.executeQuery(query);
        PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();

        while(findedOrders.next()) {
            OrderVO order = new OrderVO();
            
            order.setId(UUID.fromString(findedOrders.getString("id")));
            order.setPaymentMethod(paymentMethod[findedOrders.getInt("payment_method")]);
            order.setOrderStatus(orderStatus[findedOrders.getInt("status")]);
            order.setTotalPrice(findedOrders.getDouble("total_price"));
            order.setDate(findedOrders.getDate("order_date").toLocalDate());
            
            CustomerVO customer = new CustomerVO();
            customer.setId(UUID.fromString(findedOrders.getString("customer_id")));
            customer = CustomerDAO.findById(customer);
            
//            customer.setId(UUID.fromString(findedOrders.getString("id")));
//            customer.setCpf(findedOrders.getString("cpf"));
//            customer.setName(findedOrders.getString("name"));
//            customer.setPhoneNumber(findedOrders.getString("phone_number"));
//            customer.setAddress(findedOrders.getString("address"));
            
            order.setCustomer(customer);

            orders.add(order);
        }

        return orders;
    }
    
    public static OrderVO findById(OrderVO order) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "SELECT * FROM orders WHERE id=?::uuid";
    	
    	PreparedStatement statement;
    	
    	ResultSet findedOrder;
    	
    	statement = connection.prepareStatement(query);
    	statement.setString(1, order.getId().toString());
    	
    	findedOrder = statement.executeQuery();
    	
    	if(!findedOrder.next()) {
    		return null;
    	}
    	
    	CustomerVO customer = new CustomerVO();
    	customer.setId(UUID.fromString(findedOrder.getString("customer_id")));
        customer = CustomerDAO.findById(customer);
    	
    	PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();
    	
        OrderVO findedOrderVO = new OrderVO();
    	findedOrderVO.setId(UUID.fromString(findedOrder.getString("id")));
    	findedOrderVO.setCustomer(customer);
    	findedOrderVO.setPaymentMethod(paymentMethod[findedOrder.getInt("payment_method")]);
        findedOrderVO.setOrderStatus(orderStatus[findedOrder.getInt("status")]);
        findedOrderVO.setDate(findedOrder.getDate("order_date").toLocalDate());
    	
    	return findedOrderVO;
    }

    public static List<OrderVO> findByCustomer(CustomerVO customer) throws SQLException {
    	Connection connection = getConnection();
		String query = "SELECT * FROM orders WHERE customer_id=?::uuid";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, customer.getId().toString());
		
        ResultSet findedOrders;
        findedOrders = statement.executeQuery(query);
        
        if(!findedOrders.next()) {
        	return null;
        }

        List<OrderVO> orders = new ArrayList<OrderVO>();

        PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();

        while(findedOrders.next()) {
            OrderVO order = new OrderVO();
            
            order.setId(UUID.fromString(findedOrders.getString("id")));
            order.setCustomer(customer);
            order.setPaymentMethod(paymentMethod[findedOrders.getInt("payment_method")]);
            order.setOrderStatus(orderStatus[findedOrders.getInt("status")]);
            order.setDate(findedOrders.getDate("order_date").toLocalDate());

            orders.add(order);
        }

        return orders;
    }

    public static void update(OrderVO order) throws SQLException {
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

    public static void delete(OrderVO order) throws SQLException {
    	Connection connection = getConnection();

        String query = "DELETE FROM orders where id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, order.getId().toString());

        statement.execute();
    }
}
