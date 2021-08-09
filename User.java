import utils.UserType;

class User extends Person {
    String password;
    UserType type;

    //Sign in constructor.
    public User(
        String mName, String mCpf, String mPassword, 
        String mAdress, String mPhoneNumber, UserType mType
    ) {
        try {
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database.

            name = mName;
            cpf = mCpf;
            password = mPassword;
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

            //If password is equal, sets all attributes to user's data from DB.
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

    public boolean update(
        User userToUpdate, String mName,String mCpf, String mPassword, 
        String mPhoneNumber, UserType mType
    ) {
        try {
            if(userToUpdate.type == UserType.admin && type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            if(userToUpdate.id != id && type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Update user on database.

            userToUpdate.name = mName;
            userToUpdate.cpf = mCpf;
            userToUpdate.password = mPassword;
            userToUpdate.phoneNumber = mPhoneNumber;
            userToUpdate.type = mType;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean deleteEmployee(User employeeToDelete) {
        try {
            if(employeeToDelete.type == UserType.admin) {
                throw new Exception("That user can't be deleted.");
            }

            if(employeeToDelete.id != id && type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Delete employee on database.

            //After that, on main class, needs to delete this object on employee's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
