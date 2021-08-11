package utils;

import entities.Product;

public class OrderProduct {
    private String orderId;
    private String productId;
    private int quantity;

    public OrderProduct(String mOrderId, String mProductId, int mQuantity) {
        try {
            if(Product.findById(mProductId) == null) {
                throw new Exception("Requested product does not exist.");
            }

            this.orderId = mOrderId;
            this.productId = mProductId;
            this.quantity = mQuantity;
        } catch(Exception err) {
            //Handle exception.
        }
    }

    public boolean updateQuantity(int mQuantity) {
        try {
            //Updates quantity on database.

            this.quantity = mQuantity;

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