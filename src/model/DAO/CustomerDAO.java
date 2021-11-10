package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import errors.ValidationException;
import model.VO.CustomerVO;

public class CustomerDAO<VO extends CustomerVO> extends BaseDAO<VO> implements PersonInterDAO<VO> {
    public void insert(VO customer) throws SQLException, ValidationException {
        Connection connection = getConnection();
        String query = "insert into customers (name, cpf, phone_number, address) values (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
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

    public ResultSet findAll() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM customers";

        Statement statement = connection.createStatement();
        ResultSet findedCustomers = statement.executeQuery(query);

        return findedCustomers;
    }

    public ResultSet findById(VO customer) throws SQLException {
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

    public ResultSet findByCpf(VO customer) throws SQLException {
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

        return findedCustomer;
    }

    public void update(VO customer) throws SQLException {
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

    public void delete(VO customer) throws SQLException {
        Connection connection = getConnection();

        String query = "DELETE FROM customers where id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, customer.getId().toString());

        statement.execute();
    }
}
