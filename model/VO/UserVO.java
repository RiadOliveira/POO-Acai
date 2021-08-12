package model.VO;

import utils.UserType;

public class UserVO extends PersonVO {
    private String password;
    private UserType type;
    private boolean isLogged = false;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return this.type;
    }

    public void setType(UserType type) {
        this.type = type;
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
