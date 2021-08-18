package model.VO;

import java.util.UUID;

public class OrderProductVO {
    private UUID id;
    private OrderVO order;
    private ProductVO product;
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

    public OrderVO getOrder() {
        return this.order;
    }

    public void setOrder(OrderVO order) {
        try {
            if(order == null) {
                throw new Exception("Order can't be null (Order's product).");
            }
    
            this.order = order;
        } catch (Exception err) {
            //Handle exception.
        } 
    }

    public ProductVO getProduct() {
        return this.product;
    }

    public void setProductId(ProductVO product) {
        try {
            if(product == null) {
                throw new Exception("Product can't be null (Order's product).");
            }
    
            this.product = product;
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
        obj += "order: " + this.order.toString() + '\n';
        obj += "product: " + this.product.toString() + '\n';
        obj += "quantity: " + this.quantity;

        return obj;
    }
}
