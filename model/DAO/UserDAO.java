package model.DAO;

import java.util.UUID;

import model.VO.UserVO;
import utils.UserType;

public class UserDAO {
    public static UUID insert(
        String name, String cpf, String password, 
        String phoneNumber, UserType type
    ) {
        //Inserts user on database.
        
        return UUID.randomUUID(); //To simulates user's id from database.
    }

    public static UserVO[] findAllEmployees() {
        //Database's find method to get all employees (where type == 0);

        //To simulate database's return:
        UserVO user1 = new UserVO();
        UserVO user2 = new UserVO();

        UserVO users[] = {user1, user2};

        return users;
    }

    public static UserVO findById(UUID id) {
        //Database's find method to get requested user;

        //To simulate database's return:
        UserVO findedUser = new UserVO();

        return findedUser;
    }

    public static UserVO findByCpf(String cpf) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        UserVO findedUser = new UserVO();

        return findedUser;
    }

    public static void update(
        UUID id, String name, String password, String phoneNumber
    ) {
        //Updates User on database.
    }

    public static void delete(UUID id) {
        //Deletes User on database.
    }
}
