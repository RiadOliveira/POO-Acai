class User {
    String id;
    String name;
    String cpf;
    String password;
    String adress;
    String phoneNumber;
    UserType type;

    //Sign in constructor.
    public User(String uName,String uCpf, String uPassword, String uAdress, String uPhoneNumber, UserType uType) {
        try {
            //Find method on database to verify if exists a user with this cpf.

            //If exists, throw Exception.

            //Else, insert a new user on database and set object attributes.

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
            //Find method on database to verify if a user with this cpf exists.

            //If not exists throw Exception.

            //Else:
            String findedPassword = "passwordTest"; //To simulate database's password.

            if(!password.equals(findedPassword)) {
                throw new Exception("Exception message");
            }

            //If password is equal, fills all attributes of the object with user's data from DB.
        } catch (Exception err) {
            //Handle the exception.
        }
    }
}
