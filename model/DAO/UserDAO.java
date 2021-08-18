package model.DAO;

import java.util.UUID;

import model.VO.UserVO;

public class UserDAO {
    public static UUID insert(UserVO user) {
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
        //Updates User on database using its id.
    }

    public static void delete(UserVO user) {
        //Deletes User on database using its id.
    }
}
