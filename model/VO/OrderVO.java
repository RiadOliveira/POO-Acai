package model.VO;

import java.time.LocalDate;

import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderVO {
    private String id;
    private OrderProductVO[] orderProducts;
    private String customerId;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private LocalDate date;
    private double totalPrice;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderProductVO[] getOrderProducts() {
        return this.orderProducts;
    }

    public void setOrderProducts(OrderProductVO[] orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
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
        this.totalPrice = totalPrice;
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
