package utils;

import entities.Product;

public class OrderProduct {
    private String productId;
    private int quantity;

    public OrderProduct(String mProductId, int mQuantity) {
        try {
            if(Product.findById(mProductId) == null) {
                throw new Exception("Requested product does not exist.");
            }

            this.productId = mProductId;
            this.quantity = mQuantity;
        } catch(Exception err) {
            //Handle exception.
        }
    }

    public String getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }
}