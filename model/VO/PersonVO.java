package model.VO;

import java.util.UUID;

class PersonVO {
    private UUID id;
    private String name;
    private String cpf;
    private String phoneNumber;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        try {
            if(cpf.length() != 11) {
                throw new Exception("Cpf needs to have 11 numbers.");
            }

            for (int ind = 0; ind < cpf.length(); ind++) {
                if (cpf.charAt(ind) < '0' && cpf.charAt(ind) > '9') {
                    throw new Exception("Cpf needs to have only numbers.");
                }
            }

            this.cpf = cpf;
        } catch (Exception err) {
            //Handle exception.
        }
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            if(phoneNumber.length() < 10) {
                throw new Exception("Phone number needs to have at least 10 numbers.");
            }

            for (int ind = 0; ind < phoneNumber.length(); ind++) {
                if (phoneNumber.charAt(ind) < '0' && phoneNumber.charAt(ind) > '9') {
                    throw new Exception("phoneNumber needs to have only numbers.");
                }
            }

            this.phoneNumber = phoneNumber;
        } catch (Exception err) {
            //Handle exception.
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
