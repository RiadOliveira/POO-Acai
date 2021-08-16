package model.VO;

import java.util.UUID;

public class OrderProductVO {
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private int quantity;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        try {
            if(id == null) { //UUID already can't be created from empty string.
                throw new Exception("Order's product id can't be null.");
            }
    
            this.id = id;
        } catch (Exception err) {
            //Handle exception.
        } 
    }

    public UUID getOrderId() {
        return this.orderId;
    }

    public void setOrderId(UUID orderId) {
        try {
            if(orderId == null) { //UUID already can't be created from empty string.
                throw new Exception("Order's id can't be null (Order's product).");
            }
    
            this.orderId = orderId;
        } catch (Exception err) {
            //Handle exception.
        } 
    }

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(UUID productId) {
        try {
            if(productId == null) { //UUID already can't be created from empty string.
                throw new Exception("Product's id can't be null (Order's product).");
            }
    
            this.productId = productId;
        } catch (Exception err) {
            //Handle exception.
        } 
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        try {
            if(quantity <= 0) {
                throw new Exception("Quantity of an orderProduct needs to be higher than zero.");
            }

            this.quantity = quantity;
        } catch (Exception err) {
            //Handle exception.
        }
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "orderId: " + this.orderId + '\n';
        obj += "productId: " + this.productId + '\n';
        obj += "quantity: " + this.quantity;

        return obj;
    }
}
