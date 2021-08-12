package model.BO;

import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {    
    public static boolean create(
        CustomerVO customer, String name, String cpf, 
        String adress, String phoneNumber
    ) {
        try {
            if(CustomerDAO.findByCpf(cpf) != null) {
                throw new Exception("A customer with this cpf already exists.");
            }

            String customerId = CustomerDAO.insert(name, cpf, adress, phoneNumber);

            customer.setId(customerId);
            customer.setName(name);
            customer.setCpf(cpf);
            customer.setAdress(adress);
            customer.setPhoneNumber(phoneNumber);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
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

    public static boolean update(
        CustomerVO customer, String name, String adress, String phoneNumber
    ) {
        try {    
            if(CustomerDAO.findById(customer.getId()) == null) {
                throw new Exception("Customer not found.");
            }

            CustomerDAO.update(customer.getId(), name, adress, phoneNumber);

            customer.setName(name);
            customer.setAdress(adress);
            customer.setPhoneNumber(phoneNumber);
    
            return true;
        } catch (Exception err) {
            //Handle exception.

            return false;
        }
    }

    public static boolean delete(CustomerVO customer) {
        try {
            if(CustomerDAO.findById(customer.getId()) == null) {
                throw new Exception("Customer not found.");
            }

            CustomerDAO.delete(customer.getId());
    
            return true;             
        } catch (Exception err) {
            //Handle exception.

            return false;
        }
    }
}
