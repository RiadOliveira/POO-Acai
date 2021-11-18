package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import errors.ValidationException;
import model.DAO.UserDAO;
import model.VO.UserVO;

public class UserBO {
    private static UserDAO<UserVO> userDAO = new UserDAO<UserVO>();

    public static void signUp(UserVO user) throws Exception {
        if(userDAO.findByCpf(user) != null) {
            throw new Exception("A user with this cpf already exists.");
        }

        userDAO.insert(user);            
        user.setIsLogged(true);
    }

    public static UserVO signIn(UserVO user) throws Exception {
        UserVO findedUser = UserBO.findByCpf(user);

        if(findedUser == null) {
            throw new Exception("User not found.");
        }

        if(!user.getPassword().equals(findedUser.getPassword())) {
            throw new Exception("Invalid cpf or password.");
        }

        findedUser.setIsLogged(true);

        return findedUser;
    }

    public static void signOut(UserVO user) {
        user.setIsLogged(false);
    }

    public static UserVO findAdmin() throws SQLException, ValidationException {
        UserVO admin = new UserVO();
        ResultSet findedAdmin = userDAO.findAdmin();

        admin.setId(UUID.fromString(findedAdmin.getString("id")));
        admin.setName(findedAdmin.getString("name"));
        admin.setCpf(findedAdmin.getString("cpf"));
        admin.setPhoneNumber(findedAdmin.getString("phone_number"));
        admin.setPassword(findedAdmin.getString("password"));
        admin.setIsAdmin(findedAdmin.getBoolean("is_admin"));

        return admin;
    }

    public static List<UserVO> findAll() throws SQLException, ValidationException {
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
    }

    public static UserVO findById(UserVO user) throws SQLException, ValidationException {
        UserVO findedUser = new UserVO();
        ResultSet findedUserDB = userDAO.findById(user);

        findedUser.setId(UUID.fromString(findedUserDB.getString("id")));
        findedUser.setName(findedUserDB.getString("name"));
        findedUser.setCpf(findedUserDB.getString("cpf"));
        findedUser.setPhoneNumber(findedUserDB.getString("phone_number"));
        findedUser.setPassword(findedUserDB.getString("password"));
        findedUser.setIsAdmin(findedUserDB.getBoolean("is_admin"));

        return findedUser;
    }

    public static UserVO findByCpf(UserVO user) throws SQLException, ValidationException {
        UserVO findedUser = new UserVO();
        ResultSet findedUserDB = userDAO.findByCpf(user);

        findedUser.setId(UUID.fromString(findedUserDB.getString("id")));
        findedUser.setName(findedUserDB.getString("name"));
        findedUser.setCpf(findedUserDB.getString("cpf"));
        findedUser.setPhoneNumber(findedUserDB.getString("phone_number"));
        findedUser.setPassword(findedUserDB.getString("password"));
        findedUser.setIsAdmin(findedUserDB.getBoolean("is_admin"));

        return findedUser;
    }

    public static List<UserVO> findEmployeesByName(List<UserVO> allEmployees, String searchedName) {
        int findedEmployeesLength = 0;
        int findedEmployeesPositions[] = new int[allEmployees.size()];

        for(int ind=0, i=0 ; ind < allEmployees.size() ; ind++) {
            if(
                allEmployees.get(ind).getName().toLowerCase().
                contains(searchedName.toLowerCase())
            ) {
                findedEmployeesLength++;
                findedEmployeesPositions[i++] = ind;
            }
        }

        List<UserVO> findedEmployees = new ArrayList<UserVO>();

        for(int ind=0 ; ind<findedEmployeesLength ; ind++) {
            findedEmployees.add(allEmployees.get(findedEmployeesPositions[ind]));
        }

        return findedEmployees;
    }

    public static void update(UserVO user) throws Exception {
        if(userDAO.findById(user) == null)  {
            throw new Exception("User not found.");
        }
        
        userDAO.update(user);
    }

    public static void delete(UserVO user) throws Exception {
            UserVO findedUser = UserBO.findById(user);

            if(findedUser == null)  {
                throw new Exception("User not found.");
            }

            if(findedUser.getIsAdmin()) {
                throw new Exception("That user can't be deleted.");
            }

            userDAO.delete(user);
    }
}
