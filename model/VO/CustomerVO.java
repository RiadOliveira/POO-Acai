package model.VO;

public class CustomerVO extends PersonVO {
    private String adress;

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        try {
            if(adress == null || adress.equals("")) {
                throw new Exception("Adress can't be null or empty.");
            }

            this.adress = adress;
        } catch (Exception err) {
            //Handle exception.
        } 
    }

    public String toString() {
        return super.toString() + "adress: " + this.adress;
    }
}
