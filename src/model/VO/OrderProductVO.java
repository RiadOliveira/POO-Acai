package model.VO;

import java.util.UUID;

import errors.ValidationException;

public class OrderProductVO {
    private UUID id;
    private OrderVO order;
    private ProductVO product;
    private int quantity;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) throws ValidationException {
        if(id == null) { //UUID can't be created from empty string.
            throw new ValidationException("Order's product id can't be null.");
        }

        this.id = id;
    }

    public OrderVO getOrder() {
        return this.order;
    }

    public void setOrder(OrderVO order) throws ValidationException {
        if(order == null) {
            throw new ValidationException("Order can't be null (Order's product).");
        }

        this.order = order;
    }

    public ProductVO getProduct() {
        return this.product;
    }

    public void setProduct(ProductVO product) throws ValidationException {
        if(product == null) {
            throw new ValidationException("Product can't be null (Order's product).");
        }

        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) throws ValidationException {
        if(quantity <= 0) {
            throw new ValidationException("Quantity of an orderProduct must be greater than zero.");
        }

        this.quantity = quantity;
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "quantity: " + this.quantity  + '\n' + '\n';
        obj += "order: " + '\n' + this.order.toString() + '\n' + '\n';
        obj += "product: " + '\n' + this.product.toString();

        return obj;
    }
}
