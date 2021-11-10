package model.BO;

import java.util.List;
import java.util.UUID;

import errors.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DAO.CustomerDAO;
import model.VO.CustomerVO;

public class CustomerBO {   
    private static CustomerDAO<CustomerVO> customerDAO = new CustomerDAO<CustomerVO>();
    
    public static void insert(CustomerVO customer) throws Exception {
        if(customerDAO.findByCpf(customer) != null) {
            throw new Exception("A customer with this cpf already exists.");
        }

        customerDAO.insert(customer);
    }

    public static List<CustomerVO> findByName(List<CustomerVO> allCustomers, String searchedName) {
        int findedCustomersLength = 0;
        int findedCustomersPositions[] = new int[allCustomers.size()];

        for(int ind=0, i=0 ; ind < allCustomers.size() ; ind++) {
            if(
                allCustomers.get(ind).getName().toLowerCase().
                contains(searchedName.toLowerCase())
            ) {
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

    public static List<CustomerVO> findAll() throws SQLException, ValidationException {
        List<CustomerVO> customers = new ArrayList<CustomerVO>();
        ResultSet findedCustomers = customerDAO.findAll();

        while(findedCustomers.next()) {
            CustomerVO customer = new CustomerVO();
            
            customer.setId(UUID.fromString(findedCustomers.getString("id")));
            customer.setName(findedCustomers.getString("name"));
            customer.setCpf(findedCustomers.getString("cpf"));
            customer.setPhoneNumber(findedCustomers.getString("phone_number"));
            customer.setAddress(findedCustomers.getString("address"));

            customers.add(customer);
        }

        return customers;
    }

    public static CustomerVO findById(CustomerVO customer) throws SQLException, ValidationException {
        CustomerVO findedCustomer = new CustomerVO();
        ResultSet findedCustomerDB = customerDAO.findById(customer);

        findedCustomer.setId(UUID.fromString(findedCustomerDB.getString("id")));
        findedCustomer.setName(findedCustomerDB.getString("name"));
        findedCustomer.setCpf(findedCustomerDB.getString("cpf"));
        findedCustomer.setPhoneNumber(findedCustomerDB.getString("phone_number"));
        findedCustomer.setAddress(findedCustomerDB.getString("address"));

        return findedCustomer;
    }

    public static CustomerVO findByCpf(CustomerVO customer) throws SQLException, ValidationException {
        CustomerVO findedCustomer = new CustomerVO();
        ResultSet findedCustomerDB = customerDAO.findById(customer);

        findedCustomer.setId(UUID.fromString(findedCustomerDB.getString("id")));
        findedCustomer.setName(findedCustomerDB.getString("name"));
        findedCustomer.setCpf(findedCustomerDB.getString("cpf"));
        findedCustomer.setPhoneNumber(findedCustomerDB.getString("phone_number"));
        findedCustomer.setAddress(findedCustomerDB.getString("address"));

        return findedCustomer;
    }

    public static void update(CustomerVO customer) throws Exception {
        if(customerDAO.findById(customer) == null) {
            throw new Exception("Customer not found.");
        }

        customerDAO.update(customer);
    }

    public static void delete(CustomerVO customer) throws Exception {
        if(customerDAO.findById(customer) == null) {
            throw new Exception("Customer not found.");
        }

        customerDAO.delete(customer);
    }
}
