package model.VO;

import java.util.UUID;

import errors.ValidationException;

public abstract class PersonVO {
    private UUID id;
    private String name;
    private String cpf;
    private String phoneNumber;
    
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) throws ValidationException {
        if(id == null) { //UUID can't be created from empty string.
            throw new ValidationException("Person's id can't be null.");
        }

        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws ValidationException {
        if(name == null || name.equals("")) {
            throw new ValidationException("Person's name can't be null or empty.");
        }

        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) throws ValidationException {
        if(cpf == null)  {
            throw new ValidationException("Person's cpf can't be null.");
        }

        if(cpf.length() != 11) {
            throw new ValidationException("Person's cpf must be 11 in length.");
        }

        for (int ind=0 ; ind < cpf.length() ; ind++) {
            if (cpf.charAt(ind) < '0' || cpf.charAt(ind) > '9') {
                throw new ValidationException("Person's cpf must have only numbers.");
            }
        }

        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws ValidationException {
        if(phoneNumber == null)  {
            throw new ValidationException("Person's phone number can't be null.");
        }

        if(phoneNumber.length() < 10) { //Including DDD.
            throw new ValidationException("Person's phone number must have at least 10 numbers.");
        }

        this.phoneNumber = phoneNumber;
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
