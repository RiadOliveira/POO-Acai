package model.BO;

import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {    
    public static CustomerVO create(
        String name, String cpf, 
        String adress, String phoneNumber
    ) {
        try {
            if(CustomerDAO.findByCpf(cpf) != null) {
                throw new Exception("A customer with this cpf already exists.");
            }

            CustomerVO customer = CustomerDAO.insert(name, cpf, adress, phoneNumber);

            if(customer == null) {
                throw new Exception("Internal server error.");
            }

            return customer;
        } catch(Exception err) {
            //Handle the exception.

            return null;
        }
    }

    public static CustomerVO[] findByName(CustomerVO allCustomers[], String searchedName) {
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

    public static CustomerVO update(
        CustomerVO customer, String name, String adress, String phoneNumber
    ) {
        return CustomerDAO.update(customer.getId(), name, adress, phoneNumber);
    }

    public static boolean delete(CustomerVO customer) {
        //After that, on main class, needs to delete this object on customer's array.
        return CustomerDAO.delete(customer.getId()); 
    }
}
