package model.BO;

import model.VO.UserVO;
import utils.UserType;

public class UserBO {
    public static boolean signUp(
        UserVO user, String name, String cpf, String password, 
        String phoneNumber, UserType type
    ) {
        try {
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database.

            //Needs to insert id of User.
            user.setName(name);
            user.setCpf(cpf);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setType(type);
            user.setIsLogged(true);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean signIn(UserVO user) {
        try {
            //Uses database's find method to verify if an user with this cpf exists.

            //If not exists or the user, throw an Exception.

            //Else:
            String findedPassword = "passwordTest"; //To simulate database's password.

            if(!user.getPassword().equals(findedPassword)) {
                throw new Exception("Invalid cpf or password.");
            }

            //If password is equal, sets all attributes to user's data from DB.

            user.setIsLogged(true);

            return true;
        } catch (Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static void signOut(UserVO user) {
        user.setIsLogged(false);
    }

    public static UserVO[] findAllEmployees() {
        //Database's find method to get all employees (where type == 0);

        //To simulate database's return:
        UserVO user1 = new UserVO();
        UserVO user2 = new UserVO();

        UserBO.signUp(user1, "name1", "cpf1", "password1", "phoneNumber1", UserType.admin);
        UserBO.signUp(user2, "name2", "cpf2", "password2", "phoneNumber2", UserType.employee);

        UserVO users[] = {user1, user2};

        return users;
    }

    public static UserVO findById(String id) {
        //Database's find method to get requested user;

        //To simulate database's return:
        UserVO findedUser = new UserVO();

        UserBO.signUp(findedUser, "name1", "cpf1", "password1", "phoneNumber1", UserType.admin);

        return findedUser;
    }

    public static boolean update(
        UserVO user, String name, String password, String phoneNumber
    ) {
        try {
            //Update user on database.

            user.setName(name);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean delete(UserVO user) { //Verify if can pass only employee's id.
        try {
            if(user.getType() == UserType.admin) {
                throw new Exception("That user can't be deleted.");
            }

            //Delete employee on database.

            //After that, on main class, needs to delete this object on employee's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
