package model.VO;

import java.time.LocalDate;
import java.util.UUID;

import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderVO {
    private UUID id;
    private OrderProductVO[] orderProducts;
    private UUID customerId;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private LocalDate date;
    private double totalPrice;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderProductVO[] getOrderProducts() {
        return this.orderProducts;
    }

    public void setOrderProducts(OrderProductVO[] orderProducts) {
        this.orderProducts = orderProducts;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return this.status;
    }

    public void setOrderStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        try {
            if(totalPrice <= 0) {
                throw new Exception("Total price of an order needs to be higher than zero.");
            }

            this.totalPrice = totalPrice;
        } catch (Exception err) {
            //Handle exception.
        }
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "orderProducts: " + this.orderProducts + '\n';
        obj += "customerId: " + this.customerId + '\n';
        obj += "paymentMethod: " + this.paymentMethod + '\n';
        obj += "status: " + this.status + '\n';
        obj += "date: " + this.date + '\n';
        obj += "totalPrice: " + this.totalPrice;

        return obj;
    }
}
