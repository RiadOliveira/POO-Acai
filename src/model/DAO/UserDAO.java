package src.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.UUID;

import src.model.VO.UserVO;

public class UserDAO extends BaseDAO {
    public static void insert(UserVO user) throws SQLException { 
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

    public static UserVO[] findAllEmployees() {
        //Database's find method to get all employees (where type == 0);

        //To simulate database's return:
        UserVO user1 = new UserVO();
        UserVO user2 = new UserVO();

        UserVO users[] = {user1, user2};

        return users;
    }

    public static UserVO findById(UserVO user) {
        //Database's find method to get requested user;

        //To simulate database's return:
        return user;
    }

    public static boolean findByCpf(UserVO user) throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM users WHERE cpf=?";

        PreparedStatement statement;

        ResultSet findedUser;

        statement = connection.prepareStatement(query);
        statement.setString(1, user.getCpf());

        findedUser = statement.executeQuery();

        if(!findedUser.next()) {
            return false;
        }

        user.setId(UUID.fromString(findedUser.getString("id")));
        user.setName(findedUser.getString("name"));
        user.setCpf(findedUser.getString("cpf"));
        user.setPhoneNumber(findedUser.getString("phone_number"));
        user.setPassword(findedUser.getString("password"));
        user.setIsAdmin(findedUser.getBoolean("is_admin"));

        return true;
    }

    public static void update(UserVO user) {
        //Update User on database using its id.
    }

    public static void delete(UserVO user) {
        //Delete User on database using its id.
    }
}
