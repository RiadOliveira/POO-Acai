package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.CustomerVO;

public class CustomerDAO<Customer extends CustomerVO> extends BaseDAO<Customer> {
    public void insert(Customer customer) throws SQLException {
        Connection connection = getConnection();
        String query = "insert into customers (name, cpf, phone_number, address) values (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getCpf());
        statement.setString(3, customer.getPhoneNumber());
        statement.setString(4, customer.getAddress());

        statement.execute();

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if(generatedKeys.next()) {
            customer.setId(UUID.fromString(generatedKeys.getString(1)));
        } else {
            throw new SQLException("User's ID not found on database");
        }
    }

    public List<CustomerVO> findAll() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM customers";

        Statement statement;
        ResultSet findedCustomers;
        List<CustomerVO> customers = new ArrayList<CustomerVO>();

        statement = connection.createStatement();

        findedCustomers = statement.executeQuery(query);

        while(findedCustomers.next()) {
            CustomerVO customer = new CustomerVO();
            
            customer.setId(UUID.fromString(findedCustomers.getString("id")));
            customer.setName(findedCustomers.getString("name"));
            customer.setCpf(findedCustomers.getString("cpf"));
            customer.setPhoneNumber(findedCustomers.getString("phone_number"));
            customer.setAddress(findedCustomers.getString("address"));

            customers.add(customer);
        }

        return customers;
    }

    public ResultSet findById(Customer customer) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM customers WHERE id=?::uuid";

        PreparedStatement statement;

        ResultSet findedCustomer;

        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getId().toString());

        findedCustomer = statement.executeQuery();

        if(!findedCustomer.next()) {
            return null;
        }

        return findedCustomer;
    }

    public CustomerVO findByCpf(CustomerVO customer) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM customers WHERE cpf=?";

        PreparedStatement statement;

        ResultSet findedCustomer;

        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getCpf());

        findedCustomer = statement.executeQuery();

        if(!findedCustomer.next()) {
            return null;
        }

        CustomerVO findedCustomerVO = new CustomerVO();

        findedCustomerVO.setId(UUID.fromString(findedCustomer.getString("id")));
        findedCustomerVO.setName(findedCustomer.getString("name"));
        findedCustomerVO.setCpf(findedCustomer.getString("cpf"));
        findedCustomerVO.setPhoneNumber(findedCustomer.getString("phone_number"));
        findedCustomerVO.setAddress(findedCustomer.getString("address"));

        return findedCustomerVO;
    }

    public void update(Customer customer) throws SQLException {
        Connection connection = getConnection();

        String query = "UPDATE customers SET name=?, phone_number=?, address=? WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getPhoneNumber());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getId().toString());

        statement.execute();
    }

    public void delete(Customer customer) throws SQLException {
        Connection connection = getConnection();

        String query = "DELETE FROM customers where id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getId().toString());

        statement.execute();
    }
}
