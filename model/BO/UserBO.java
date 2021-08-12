package model.BO;

import java.util.UUID;

import model.DAO.UserDAO;
import model.VO.UserVO;
import utils.UserType;

public class UserBO {
    public static boolean signUp(
        UserVO user, String name, String cpf, String password, 
        String phoneNumber, UserType type
    ) {
        try {
            if(UserDAO.findByCpf(cpf) != null) {
                throw new Exception("A user with this cpf already exists.");
            }

            UUID userId = UserDAO.insert(name, cpf, password, phoneNumber, type);

            user.setId(userId);
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

    public static boolean signIn(UserVO user, String cpf, String password) {
        try {
            UserVO findedUser = UserDAO.findByCpf(cpf);

            if(findedUser == null) {
                throw new Exception("User not found.");
            }

            if(!findedUser.getPassword().equals(password)) {
                throw new Exception("Invalid cpf or password.");
            }

            user = findedUser;

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

    public static boolean update(
        UserVO user, String name, String password, String phoneNumber
    ) {
        try {
            if(UserDAO.findById(user.getId()) == null)  {
                throw new Exception("User not found.");
            }
            
            UserDAO.update(user.getId(), name, password, phoneNumber);

            user.setName(name);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean delete(UserVO user) {
        try {
            if(user.getType() == UserType.admin) {
                throw new Exception("That user can't be deleted.");
            }

            if(UserDAO.findById(user.getId()) == null)  {
                throw new Exception("User not found.");
            }

            UserDAO.delete(user.getId());
            user = null;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
