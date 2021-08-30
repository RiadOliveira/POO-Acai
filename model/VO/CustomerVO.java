package model.VO;

public class CustomerVO extends PersonVO {
    private String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        try {
            if(address == null || address.equals("")) {
                throw new Exception("Address can't be null or empty.");
            }

            this.address = address;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public String toString() {
        return super.toString() + "address: " + this.address;
    }
}
