package model.VO;

public class CustomerVO extends PersonVO {
    private String adress;

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String toString() {
        return super.toString() + "adress: " + this.adress;
    }
}
