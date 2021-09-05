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

            UserVO findeduser = UserDAO.findByCpf(user); //In order to get user's id.
            
            user.setId(findeduser.getId());
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
            UserVO findedUser = UserDAO.findByCpf(user);

            if(findedUser == null) {
                throw new Exception("User not found.");
            }

            if(!user.getPassword().equals(findedUser.getPassword())) {
                throw new Exception("Invalid cpf or password.");
            }

            user.setId(findedUser.getId());
            user.setName(findedUser.getName());
            user.setPhoneNumber(findedUser.getPhoneNumber());
            user.setIsAdmin(findedUser.getIsAdmin());
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
