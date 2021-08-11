package entities;

import utils.UserType;

public class User extends Person {
    String password;
    UserType type;

    //Sign up constructor.
    public User(
        String mName, String mCpf, String mPassword, 
        String mAdress, String mPhoneNumber, UserType mType
    ) {
        try {
            //Uses database's find method to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user into database.

            //Needs to insert id of User.
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

            //If not exists or the user, throw an Exception.

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

    public static User[] findAllEmployees() {
        //Database's find method to get all employees (where type == 0);

        //To simulate database's return:
        User user1 = new User("cpf01", "password01");
        User user2 = new User("cpf02", "password02");

        User users[] = {user1, user2};

        return users;
    }

    public static User findById(String id) {
        //Database's find method to get requested user;

        //To simulate database's return:
        User findedUser = new User("cpf01", "password01");

        return findedUser;
    }

    public boolean update(String mName, String mPassword, String mPhoneNumber) {
        try {
            //Update user on database.

            name = mName;
            password = mPassword;
            phoneNumber = mPhoneNumber;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean delete() {
        try {
            if(type == UserType.admin) {
                throw new Exception("That user can't be deleted.");
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
