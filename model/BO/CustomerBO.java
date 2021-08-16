package model.BO;

import java.util.UUID;

import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {    
    public static boolean create(
        CustomerVO customer, String name, String cpf, 
        String phoneNumber, String adress
    ) {
        try {
            if(CustomerDAO.findByCpf(cpf) != null) {
                throw new Exception("A customer with this cpf already exists.");
            }

            UUID customerId = CustomerDAO.insert(name, cpf, phoneNumber, adress);

            customer.setId(customerId);
            customer.setName(name);
            customer.setCpf(cpf);
            customer.setPhoneNumber(phoneNumber);
            customer.setAdress(adress);

            return true;
        } catch(Exception err) {
            //Handle exception.

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
        CustomerVO customer, String name, String phoneNumber, String adress
    ) {
        try {    
            if(CustomerDAO.findById(customer.getId()) == null) {
                throw new Exception("Customer not found.");
            }

            CustomerDAO.update(customer.getId(), name, phoneNumber, adress);

            customer.setName(name);
            customer.setPhoneNumber(phoneNumber);
            customer.setAdress(adress);
    
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
            customer = null;
    
            return true;             
        } catch (Exception err) {
            //Handle exception.

            return false;
        }
    }
}
