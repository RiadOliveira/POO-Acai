package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import errors.ValidationException;
import model.VO.UserVO;

public class UserDAO<VO extends UserVO> extends BaseDAO<VO> implements PersonInterDAO<VO> {
    public void insert(VO user) throws SQLException, ValidationException { 
    	Connection connection = getConnection();
		String query = "insert into users (name, cpf, phone_number, password, is_admin) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
		statement.setString(1, user.getName());
		statement.setString(2, user.getCpf());
		statement.setString(3, user.getPhoneNumber());
		statement.setString(4, user.getPassword());
		statement.setBoolean(5, user.getIsAdmin());

		statement.execute();

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if(generatedKeys.next()) {
        	user.setId(UUID.fromString(generatedKeys.getString(1)));
        } else {
        	throw new SQLException("User's ID not found on database");
        }
    }

    public ResultSet findAll() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE is_admin=false";

        Statement statement = connection.createStatement();
        ResultSet findedEmployees = statement.executeQuery(query);

        return findedEmployees;
    }

    public ResultSet findById(VO user) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE id=?::uuid";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet findedUser;

        statement.setString(1, user.getId().toString());

        findedUser = statement.executeQuery();

        if(!findedUser.next()) {
            return null;
        }

        return findedUser;
    }

    public ResultSet findByCpf(VO user) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE cpf=?";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet findedUser;
        
        statement.setString(1, user.getCpf());
        findedUser = statement.executeQuery();

        if(!findedUser.next()) {
            return null;
        }

        return findedUser;
    }

    public void update(VO user) throws SQLException {
        Connection connection = getConnection();

        String query = "UPDATE users SET name=?, phone_number=?, password=? WHERE id=?::uuid";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getName());
        statement.setString(2, user.getPhoneNumber());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getId().toString());

        statement.execute();
    }

    public void delete(VO user) throws SQLException {
        Connection connection = getConnection();

        String query = "DELETE FROM users where id=?::uuid";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getId().toString());

        statement.execute();
    }
}
