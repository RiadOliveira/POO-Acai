package utils;

import model.DAO.ProductDAO;

public class OrderProduct {
    private String orderId;
    private String productId;
    private int quantity;

    public OrderProduct(String orderId, String productId, int quantity) {
        try {
            if(ProductDAO.findById(productId) == null) {
                throw new Exception("Requested product does not exist.");
            }

            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
        } catch(Exception err) {
            //Handle exception.
        }
    }

    public boolean updateQuantity(int quantity) {
        try {
            //Updates quantity on database.

            this.quantity = quantity;

            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public boolean delete() {
        try {
            //Delete OrderProduct from database.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }
}