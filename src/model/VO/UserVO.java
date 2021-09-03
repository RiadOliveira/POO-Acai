package src.model.VO;


public class UserVO extends PersonVO {
    private String password;
    private boolean isAdmin;
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

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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
        obj += "isAdmin: " + this.isAdmin + '\n';
        obj += "isLogged: " + this.isLogged;

        return obj;
    }
}
