package model.BO;

import java.util.UUID;

import model.DAO.UserDAO;
import model.VO.UserVO;
import utils.UserType;

public class UserBO {
    public static boolean signUp(UserVO user) {
        try {
            if(UserDAO.findByCpf(user) != null) {
                throw new Exception("A user with this cpf already exists.");
            }

            UUID userId = UserDAO.insert(user);

            user.setId(userId);
            user.setIsLogged(true);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean signIn(UserVO user) {
        try {
            UserVO findedUser = UserDAO.findByCpf(user);

            if(findedUser == null) {
                throw new Exception("User not found.");
            }

            if(!findedUser.getPassword().equals(user.getPassword())) {
                throw new Exception("Invalid cpf or password.");
            }

            user = findedUser;

            user.setIsLogged(true);

            return true;
        } catch (Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static void signOut(UserVO user) {
        user.setIsLogged(false);
    }

    public static boolean update(UserVO user) {
        try {
            if(UserDAO.findById(user) == null)  {
                throw new Exception("User not found.");
            }
            
            UserDAO.update(user);

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean delete(UserVO user) {
        try {
            if(user.getType() == UserType.admin) {
                throw new Exception("That user can't be deleted.");
            }

            if(UserDAO.findById(user) == null)  {
                throw new Exception("User not found.");
            }

            UserDAO.delete(user);
            user = null;

            return true;
        } catch(Exception err) {
            //Handle exception.

            return false;
        }
    }
}
