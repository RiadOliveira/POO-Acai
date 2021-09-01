package src.model.VO;

import src.utils.UserType;

public class UserVO extends PersonVO {
    private String password;
    private UserType type;
    private boolean isLogged = false;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        try {
            if(password == null) {
                throw new Exception("User's password can't be null.");
            }

            if(password.length() < 6) {
                throw new Exception("User's password must have at least 6 characters.");
            }
    
            this.password = password;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public UserType getType() {
        return this.type;
    }

    public void setType(UserType type) {
        try {
            if(type == null) {
                throw new Exception("User's type can't be null.");
            }
    
            this.type = type;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public boolean getIsLogged() {
        return this.isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String toString() {
        String obj = super.toString();
        obj += "password: " + this.password + '\n';
        obj += "type: " + this.type + '\n';
        obj += "isLogged: " + this.isLogged;

        return obj;
    }
}
