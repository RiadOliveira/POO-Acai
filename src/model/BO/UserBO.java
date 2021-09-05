package src.model.BO;

import src.model.DAO.UserDAO;
import src.model.VO.UserVO;

public class UserBO {
    public static boolean signUp(UserVO user) {
        try {
            if(UserDAO.findByCpf(user) != null) {
                throw new Exception("A user with this cpf already exists.");
            }

            UserDAO.insert(user);

            user = UserDAO.findByCpf(user); //In order to get user's id.
            user.setIsLogged(true);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean signIn(UserVO user) {
        try {
            user = UserDAO.findByCpf(user);

            if(user.getId() == null) {
                throw new Exception("User not found.");
            }

            if(!user.getPassword().equals(user.getPassword())) {
                throw new Exception("Invalid cpf or password.");
            }

            user.setIsLogged(true);

            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

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
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(UserVO user) {
        try {
            if(user.getIsAdmin()) {
                throw new Exception("That user can't be deleted.");
            }

            if(UserDAO.findById(user) == null)  {
                throw new Exception("User not found.");
            }

            UserDAO.delete(user);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
