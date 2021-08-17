package model.DAO;

import java.util.UUID;

import model.VO.CustomerVO;

public class CustomerDAO {
    public static UUID insert(CustomerVO customer) {
        //Inserts customer on database.

        return UUID.randomUUID(); //To simulates customer's id from database.
    }

    public static CustomerVO[] findAll() {
        //Database's find method to get all customers;

        //To simulate database's return:
        CustomerVO customer1 = new CustomerVO();
        CustomerVO customer2 = new CustomerVO();

        CustomerVO customers[] = {customer1, customer2};

        return customers;
    }

    public static CustomerVO findById(CustomerVO customer) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        return customer;
    }

    public static CustomerVO findByCpf(CustomerVO customer) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        return customer;
    }

    public static void update(CustomerVO customer) {
        //Update customer on database using its id.
    }

    public static void delete(CustomerVO customer) {
        //Delete customer on database using its id.
    }
}
