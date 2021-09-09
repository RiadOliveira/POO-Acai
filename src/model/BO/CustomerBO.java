package model.BO;


import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {    
    public static boolean create(CustomerVO customer) {
        try {
            if(CustomerDAO.findByCpf(customer) != null) {
                throw new Exception("A customer with this cpf already exists.");
            }

            CustomerDAO.insert(customer);

            CustomerVO findedCustomer = CustomerDAO.findByCpf(customer);
            customer.setId(findedCustomer.getId());

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static CustomerVO[] findByName(CustomerVO[] allCustomers, String searchedName) {
        int findedCustomersLength = 0;
        int findedCustomersPositions[] = new int[allCustomers.length];

        for(int ind=0, i=0 ; ind<allCustomers.length ; ind++) {
            if(allCustomers[ind].getName().contains(searchedName)) {
                findedCustomersLength++;
                findedCustomersPositions[i++] = ind;
            }
        }

        CustomerVO findedCustomers[] = new CustomerVO[findedCustomersLength];

        for(int ind=0 ; ind<findedCustomersLength ; ind++) {
            findedCustomers[ind] = allCustomers[findedCustomersPositions[ind]];
        }

        return findedCustomers;
    }

    public static boolean update(CustomerVO customer) {
        try {    
            if(CustomerDAO.findById(customer) == null) {
                throw new Exception("Customer not found.");
            }

            CustomerDAO.update(customer);
    
            return true;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(CustomerVO customer) {
        try {
            if(CustomerDAO.findById(customer) == null) {
                throw new Exception("Customer not found.");
            }

            CustomerDAO.delete(customer);
    
            return true;             
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
