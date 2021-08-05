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
        String mName, String mCpf, String mPassword, 
        String mAdress, String mPhoneNumber, UserType mType
    ) {
        try {
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database and set object attributes.

            name = mName;
            cpf = mCpf;
            password = mPassword;
            adress = mAdress;
            phoneNumber = mPhoneNumber;
            type = mType;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    //Login constructor.
    public User(String cpf, String password) {
        try {
            //Uses database's find method to verify if an user with this cpf exists.

            //If not exists or the user is a customer(type == 0) (Customers can't use the application), 
            //throw an Exception.

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

    //When a user will register another (admin registering employee/customer or employee registering customer).
    public User registerNewUser(
        String mName, String mCpf, String mPassword, 
        String mAdress, String mPhoneNumber, UserType mType
    ) {
        try {
            if(mType == UserType.admin) {
                throw new Exception("The application can't has more than one admin.");
            }

            if(type == UserType.employee && mType == UserType.employee) {
                throw new Exception("The user does not have permission to execute this action.");
            }
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database and set object attributes.
            User newUser = new User(mName, mCpf, mPassword, mAdress, mPhoneNumber, mType);

            return newUser;
        } catch(Exception err) {
            //Handle the exception.

            return null;
        }
    }

    public boolean update(
        User userToUpdate, String mName,String mCpf, String mPassword, 
        String mAdress, String mPhoneNumber, UserType mType
    ) {
        try {
            if(userToUpdate.type == UserType.admin && type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            if(
                userToUpdate.id != id && type != UserType.admin && 
                userToUpdate.type == UserType.employee
            ) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Update user on database.

            userToUpdate.name = mName;
            userToUpdate.cpf = mCpf;
            userToUpdate.password = mPassword;
            userToUpdate.adress = mAdress;
            userToUpdate.phoneNumber = mPhoneNumber;
            userToUpdate.type = mType;

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
