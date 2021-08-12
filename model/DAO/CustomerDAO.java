package model.DAO;

import model.VO.CustomerVO;

public class CustomerDAO {
    public static String insert(
        String name, String cpf, 
        String phoneNumber, String adress
    ) {
        //Inserts customer on database.

        return "customerId";
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

    public static void update(
        String id, String name, String phoneNumber, String adress
    ) {
        //Update customer on database.
    }

    public static void delete(String id) {
        //Delete customer on database.
    }
}
