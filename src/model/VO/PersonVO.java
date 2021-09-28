package model.VO;

import java.util.UUID;

public abstract class PersonVO {
    private UUID id;
    private String name;
    private String cpf;
    private String phoneNumber;
    
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        try {
            if(id == null) { //UUID can't be created from empty string.
                throw new Exception("Person's id can't be null.");
            }
    
            this.id = id;
        } catch (Exception err) {
            //Handle exception
        	System.out.println(err.getMessage());
        }  
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        try {
            if(name == null || name.equals("")) {
                throw new Exception("Person's name can't be null or empty.");
            }
    
            this.name = name;
        } catch (Exception err) {
            //Handle exception
        	System.out.println(err.getMessage());
        }  
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        try {
            if(cpf == null)  {
                throw new Exception("Person's cpf can't be null.");
            }

            if(cpf.length() != 11) {
                throw new Exception("Person's cpf must be 11 in length.");
            }

            for (int ind=0 ; ind < cpf.length() ; ind++) {
                if (cpf.charAt(ind) < '0' || cpf.charAt(ind) > '9') {
                    throw new Exception("Person's cpf must have only numbers.");
                }
            }

            this.cpf = cpf;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        }
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            if(phoneNumber == null)  {
                throw new Exception("Person's phone number can't be null.");
            }

            if(phoneNumber.length() < 10) { //Including DDD.
                throw new Exception("Person's phone number must have at least 10 numbers.");
            }

            for (int ind=0 ; ind < phoneNumber.length() ; ind++) { //Verify if has only numbers.
                if (phoneNumber.charAt(ind) < '0' || phoneNumber.charAt(ind) > '9') {
                    throw new Exception("Person's phone number must have only numbers.");
                }
            }

            this.phoneNumber = phoneNumber;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        }
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "name: " + this.name + '\n';
        obj += "cpf: " + this.cpf + '\n';
        obj += "phoneNumber: " + this.phoneNumber + '\n';

        return obj;
    }
}
