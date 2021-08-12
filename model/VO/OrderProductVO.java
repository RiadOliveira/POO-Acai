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
        this.id = id;
    }

    public UUID getOrderId() {
        return this.orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
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
            //Handle the exception.
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
