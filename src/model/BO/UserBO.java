package model.BO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.DAO.UserDAO;
import model.VO.UserVO;

public class UserBO {
    private static UserDAO<UserVO> userDAO = new UserDAO<UserVO>();

    public static boolean signUp(UserVO user) {
        try {
            if(userDAO.findByCpf(user) != null) {
                throw new Exception("A user with this cpf already exists.");
            }

            userDAO.insert(user);            
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
            ResultSet findedUser = userDAO.findByCpf(user);

            if(findedUser == null) {
                throw new Exception("User not found.");
            }

            if(!user.getPassword().equals(findedUser.getString("password"))) {
                throw new Exception("Invalid cpf or password.");
            }

            user.setId(UUID.fromString(findedUser.getString("id")));
            user.setName(findedUser.getString("name"));
            user.setPhoneNumber(findedUser.getString("phone_number"));
            user.setIsAdmin(findedUser.getBoolean("is_admin"));
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

    public static List<UserVO> findAll() {
        try {
            List<UserVO> employees = new ArrayList<UserVO>();
            ResultSet findedEmployees = userDAO.findAll();

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
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return null;
        }
    }

    public static boolean findById(UserVO user) {
        try{
            ResultSet findedUser = userDAO.findById(user);
                    
            user.setName(findedUser.getString("name"));
            user.setCpf(findedUser.getString("cpf"));
            user.setPhoneNumber(findedUser.getString("phone_number"));
            user.setPassword(findedUser.getString("password"));
            user.setIsAdmin(findedUser.getBoolean("is_admin"));

            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean findByCpf(UserVO user) {
        try{
            ResultSet findedUser = userDAO.findByCpf(user);
                    
            user.setId(UUID.fromString(findedUser.getString("id")));
            user.setName(findedUser.getString("name"));
            user.setPhoneNumber(findedUser.getString("phone_number"));
            user.setPassword(findedUser.getString("password"));
            user.setIsAdmin(findedUser.getBoolean("is_admin"));

            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean update(UserVO user) {
        try {
            if(userDAO.findById(user) == null)  {
                throw new Exception("User not found.");
            }
            
            userDAO.update(user);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(UserVO user) {
        try {
            ResultSet findedUser = userDAO.findById(user);

            if(findedUser == null)  {
                throw new Exception("User not found.");
            }

            if(findedUser.getBoolean("is_admin")) {
                throw new Exception("That user can't be deleted.");
            }

            userDAO.delete(user);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
