package model.VO;

import utils.UserType;

public class User extends Person {
    private String password;
    private UserType type;

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
}
