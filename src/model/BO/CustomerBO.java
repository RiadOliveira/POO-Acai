package model.BO;

import java.util.List;
import java.util.ArrayList;

import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {   
    private static CustomerDAO<CustomerVO> customerDAO = new CustomerDAO<CustomerVO>();
    
    public static boolean create(CustomerVO customer) {
        try {
            if(customerDAO.findByCpf(customer) != null) {
                throw new Exception("A customer with this cpf already exists.");
            }

            customerDAO.insert(customer);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static List<CustomerVO> findByName(List<CustomerVO> allCustomers, String searchedName) {
        int findedCustomersLength = 0;
        int findedCustomersPositions[] = new int[allCustomers.size()];

        for(int ind=0, i=0 ; ind < allCustomers.size() ; ind++) {
            if(allCustomers.get(ind).getName().contains(searchedName)) {
                findedCustomersLength++;
                findedCustomersPositions[i++] = ind;
            }
        }

        List<CustomerVO> findedCustomers = new ArrayList<CustomerVO>();

        for(int ind=0 ; ind<findedCustomersLength ; ind++) {
            findedCustomers.add(allCustomers.get(findedCustomersPositions[ind]));
        }

        return findedCustomers;
    }

    public static boolean update(CustomerVO customer) {
        try {    
            if(customerDAO.findById(customer) == null) {
                throw new Exception("Customer not found.");
            }

            customerDAO.update(customer);
    
            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(CustomerVO customer) {
        try {
            if(customerDAO.findById(customer) == null) {
                throw new Exception("Customer not found.");
            }

            customerDAO.delete(customer);
    
            return true;             
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
