package model.VO;

public class OrderProductVO {
    private String id;
    private String orderId;
    private String productId;
    private int quantity;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
