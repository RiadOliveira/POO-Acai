package src.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

//import java.util.UUID;

import src.model.VO.UserVO;


public class UserDAO extends BaseDAO {
    public static void insert(UserVO user) { //May return User with id.
        //Insert user into database.
    	try {
			Connection connection = getConnection();
			String sql = "insert into systemuser (name, cpf, email, phoneNumber, password, isLogged) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getCpf());
			statement.setString(3,user.getEmail());
			statement.setString(4, user.getPhoneNumber());
			statement.setString(5, user.getPassword());
			statement.setBoolean(6, user.getIsLogged());
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
       
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

    public static UserVO findByCpf(UserVO user) {
        //Database's find method (where cpf == user.cpf) to get requested customer;

        //To simulate database's return:
        return user;
    }

    public static void update(UserVO user) {
        //Update User on database using its id.
    }

    public static void delete(UserVO user) {
        //Delete User on database using its id.
    }
}
