package entities;

public class Customer extends Person {
    String adress;
    
    public Customer(String name, String cpf, String adress, String phoneNumber) {
        try {
            //Uses database's find method to verify if exists a customer with this cpf.

            //If exists, throw an Exception.

            //Else, insert a new customer into database.

            //Needs to insert id of Customer.
            this.name = name;
            this.cpf = cpf;
            this.adress = adress;
            this.phoneNumber = phoneNumber;

        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Customer findById(String id) {
        //Database's find method to get requested customer;

        //To simulate database's return:
        Customer findedCustomer = new Customer("name", "cpf", "adress", "phone");

        return findedCustomer;
    }

    public static Customer[] findAll() {
        //Database's find method to get all customers;

        //To simulate database's return:
        Customer customer1 = new Customer("name1", "cpf1", "adress1", "phone1");
        Customer customer2 = new Customer("name2", "cpf2", "adress2", "phone2");

        Customer customers[] = {customer1, customer2};

        return customers;
    }

    public static Customer[] findByName(Customer allCustomers[], String searchedName) {
        int findedCustomersLength = 0;
        int findedCustomersPositions[] = new int[allCustomers.length];

        for(int ind=0, i=0 ; ind<allCustomers.length ; ind++) {
            if(allCustomers[ind].name.contains(searchedName)) {
                findedCustomersLength++;
                findedCustomersPositions[i++] = ind;
            }
        }

        Customer findedCustomers[] = new Customer[findedCustomersLength];

        for(int ind=0 ; ind<findedCustomersLength ; ind++) {
            findedCustomers[ind] = allCustomers[findedCustomersPositions[ind]];
        }

        return findedCustomers;
    }

    public boolean update(String name, String cpf, String adress, String phoneNumber) {
        try {
            //Update customer on database.

            this.name = name;
            this.cpf = cpf;
            this.adress = adress;
            this.phoneNumber = phoneNumber;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean delete() {
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
