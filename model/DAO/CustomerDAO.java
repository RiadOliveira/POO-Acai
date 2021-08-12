package model.DAO;

import model.VO.CustomerVO;

public class CustomerDAO {
    public static CustomerVO insert(
        String name, String cpf, 
        String adress, String phoneNumber
    ) {
        try {
            //Insert customer on database.
            CustomerVO customer1 = new CustomerVO();

            return customer1;
        } catch (Exception err) {
            return null;
        }
    }

    public static CustomerVO[] findAll() {
        //Database's find method to get all customers;

        //To simulate database's return:
        CustomerVO customer1 = new CustomerVO();
        CustomerVO customer2 = new CustomerVO();

        CustomerVO customers[] = {customer1, customer2};

        return customers;
    }

    public static CustomerVO findById(String id) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        CustomerVO findedCustomer = new CustomerVO();

        return findedCustomer;
    }

    public static CustomerVO findByCpf(String cpf) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        CustomerVO findedCustomer = new CustomerVO();

        return findedCustomer;
    }

    public static CustomerVO update(
        String id, String name, String adress, String phoneNumber
    ) {
        try {
            CustomerVO databaseCustomer = CustomerDAO.findById(id);

            if(databaseCustomer == null) {
                throw new Exception("Customer not found.");
            }

            databaseCustomer.setName(name);
            databaseCustomer.setAdress(adress);
            databaseCustomer.setPhoneNumber(phoneNumber);

            //Update customer on database.

            return databaseCustomer;
        } catch(Exception err) {
            return null;
        }
    }

    public static boolean delete(String id) { //Verify if can pass only customer's id.
        try {
            CustomerVO databaseCustomer = CustomerDAO.findById(id);

            if(databaseCustomer == null) {
                throw new Exception("Customer not found.");
            }

            //Delete customer on database.

            return true;
        } catch(Exception err) {
            return false;
        }
    }
}
