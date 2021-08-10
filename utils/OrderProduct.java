package utils;

import entities.Product;

public class OrderProduct {
    public String productId;
    public int quantity;

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
}