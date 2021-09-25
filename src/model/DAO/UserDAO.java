package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.UserVO;

public class UserDAO<User extends UserVO> extends BaseDAO<User> {
    public void insert(User user) throws SQLException { 
			Connection connection = getConnection();
			String query = "insert into users (name, cpf, phone_number, password, is_admin) values (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setString(2, user.getCpf());
			statement.setString(3, user.getPhoneNumber());
			statement.setString(4, user.getPassword());
			statement.setBoolean(5, user.getIsAdmin());

			statement.execute();
    }

    public List<UserVO> findAllEmployees() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE is_admin=false";

        Statement statement;
        ResultSet findedEmployees;
        List<UserVO> employees = new ArrayList<UserVO>();

        statement = connection.createStatement();

        findedEmployees = statement.executeQuery(query);

        while(findedEmployees.next()) {
            UserVO employee = new UserVO();
            
            employee.setId(UUID.fromString(findedEmployees.getString("id")));
            employee.setName(findedEmployees.getString("name"));
            employee.setCpf(findedEmployees.getString("cpf"));
            employee.setPhoneNumber(findedEmployees.getString("phone_number"));
            employee.setPassword(findedEmployees.getString("password"));
            employee.setIsAdmin(findedEmployees.getBoolean("is_admin"));

            employees.add(employee);
        }

        return employees;
    }

    public ResultSet findById(User user) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE id=?::uuid";

        PreparedStatement statement;

        ResultSet findedUser;

        statement = connection.prepareStatement(query);
        statement.setString(1, user.getId().toString());

        findedUser = statement.executeQuery();

        if(!findedUser.next()) {
            return null;
        }

        // findedUserVO.setId(UUID.fromString(findedUser.getString("id")));
        // findedUserVO.setName(findedUser.getString("name"));
        // findedUserVO.setCpf(findedUser.getString("cpf"));
        // findedUserVO.setPhoneNumber(findedUser.getString("phone_number"));
        // findedUserVO.setPassword(findedUser.getString("password"));
        // findedUserVO.setIsAdmin(findedUser.getBoolean("is_admin"));

        return findedUser;
    }

    public ResultSet findByCpf(UserVO user) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE cpf=?";

        PreparedStatement statement;

        ResultSet findedUser;

        statement = connection.prepareStatement(query);
        statement.setString(1, user.getCpf());

        findedUser = statement.executeQuery();

        if(!findedUser.next()) {
            return null;
        }

        // UserVO findedUserVO = new UserVO();

        // findedUserVO.setId(UUID.fromString(findedUser.getString("id")));
        // findedUserVO.setName(findedUser.getString("name"));
        // findedUserVO.setCpf(findedUser.getString("cpf"));
        // findedUserVO.setPhoneNumber(findedUser.getString("phone_number"));
        // findedUserVO.setPassword(findedUser.getString("password"));
        // findedUserVO.setIsAdmin(findedUser.getBoolean("is_admin"));

        return findedUser;
    }

    public void update(User user) throws SQLException {
        Connection connection = getConnection();

        String query = "UPDATE users SET name=?, phone_number=?, password=? WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPhoneNumber());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getId().toString());

        statement.execute();
    }

    public void delete(User user) throws SQLException {
        Connection connection = getConnection();

        String query = "DELETE FROM users where id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, user.getId().toString());

        statement.execute();
    }
}
