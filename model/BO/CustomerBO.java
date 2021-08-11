package model.BO;

import model.VO.CustomerVO;

public class CustomerBO {    
    public static boolean create(CustomerVO customer, String name, String cpf, String adress, String phoneNumber) {
        try {
            //Uses database's find method to verify if exists a customer with this cpf.

            //If exists, throw an Exception.

            //Else, insert a new customer into database.

            //Needs to insert id of Customer.
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

    public static CustomerVO findById(String id) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        CustomerVO findedCustomer = new CustomerVO();

        CustomerBO.create(findedCustomer, "name", "cpf", "adress", "phone");

        return findedCustomer;
    }

    public static CustomerVO[] findAll() {
        //Database's find method to get all customers;

        //To simulate database's return:
        CustomerVO customer1 = new CustomerVO();
        CustomerVO customer2 = new CustomerVO();

        CustomerBO.create(customer1, "name1", "cpf1", "adress1", "phone1");
        CustomerBO.create(customer2, "name2", "cpf2", "adress2", "phone2");

        CustomerVO customers[] = {customer1, customer2};

        return customers;
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

    public boolean update(CustomerVO customer, String name, String cpf, String adress, String phoneNumber) {
        try {
            //Update customer on database.

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

    public boolean delete(CustomerVO customer) { //Verify if can pass only customer's id.
        try {
            //Delete customer on database.

            //After that, on main class, needs to delete this object on customer's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
