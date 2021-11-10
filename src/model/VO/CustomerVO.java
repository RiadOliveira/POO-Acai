package model.VO;

import errors.ValidationException;

public class CustomerVO extends PersonVO {
    private String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) throws ValidationException {
        if(address == null || address.equals("")) {
            throw new ValidationException("Address can't be null or empty.");
        }

        this.address = address;
    }

    public String toString() {
        return super.toString() + "address: " + this.address;
    }
}
