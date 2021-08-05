class User {
    String id;
    String name;
    String cpf;
    String password;
    String adress;
    String phoneNumber;
    UserType type;

    //Sign in constructor.
    public User(
        String uName,String uCpf, String uPassword, 
        String uAdress, String uPhoneNumber, UserType uType
    ) {
        try {
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database and set object attributes.

            name = uName;
            cpf = uCpf;
            password = uPassword;
            adress = uAdress;
            phoneNumber = uPhoneNumber;
            type = uType;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    //Login constructor.
    public User(String cpf, String password) {
        try {
            //Uses database's find method to verify if a user with this cpf exists.

            //If not exists throw Exception or if the user is a customer (Customers can't use the application).

            //Else:
            String findedPassword = "passwordTest"; //To simulate database's password.

            if(!password.equals(findedPassword)) {
                throw new Exception("Invalid cpf or password.");
            }

            //If password is equal, fills all attributes of the object with user's data from DB.
        } catch (Exception err) {
            //Handle the exception.
        }
    }

    public static User findById(String id) {
        //Database's find method to get requested user;

        //To simulate database's return:
        User findedUser = new User("cpf01", "password01");

        return findedUser;
    }

    public static User[] findAllCustomers() {
        //Database's find method to get all users where type == 0 (customer);

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        User usr2 = new User("cpf02", "password02");

        User users[] = {usr1, usr2};

        return users;
    }

    public static User[] findCustomersByName(User allCustomers[], String searchedName) {
        int findedCustomersLength = 0;
        int findedCustomersPositions[] = new int[allCustomers.length];

        for(int ind=0, i=0 ; ind<allCustomers.length ; ind++) {
            if(allCustomers[ind].name.contains(searchedName)) {
                findedCustomersLength++;
                findedCustomersPositions[i++] = ind;
            }
        }

        User findedCustomers[] = new User[findedCustomersLength];

        for(int ind=0 ; ind<findedCustomersLength ; ind++) {
            findedCustomers[ind] = allCustomers[findedCustomersPositions[ind]];
        }

        return findedCustomers;
    }

    public boolean update(
        User updatedUser, String uName,String uCpf, String uPassword, 
        String uAdress, String uPhoneNumber, UserType uType
    ) {
        try {
            if(updatedUser.type == UserType.admin && type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            if(
                updatedUser.id != id && type != UserType.admin && 
                updatedUser.type == UserType.employee
            ) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Update user on database.

            updatedUser.name = uName;
            updatedUser.cpf = uCpf;
            updatedUser.password = uPassword;
            updatedUser.adress = uAdress;
            updatedUser.phoneNumber = uPhoneNumber;
            updatedUser.type = uType;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean delete(User userToDelete) {
        try {
            if(userToDelete.type == UserType.admin) {
                throw new Exception("That user can't be deleted.");
            }

            if(
                userToDelete.id != id && type != UserType.admin && 
                userToDelete.type == UserType.employee
            ) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Delete user on database.

            //After that, on main class, needs to delete this object on customers/employees' array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
