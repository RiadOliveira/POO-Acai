package model.VO;

import java.time.LocalDate;
import java.util.UUID;

import errors.ValidationException;
import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderVO {
    private UUID id;
    private CustomerVO customer;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private LocalDate date;
    private double totalPrice;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) throws ValidationException {
        if(id == null) { //UUID can't be created from empty string.
            throw new ValidationException("Order's id can't be null.");
        }

        this.id = id;
    }

    public CustomerVO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerVO customer) throws ValidationException {
        if(customer == null) {
            throw new ValidationException("Order's customer can't be null.");
        }

        this.customer = customer;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) throws ValidationException {
        if(paymentMethod == null) {
            throw new ValidationException("Order's payment method can't be null.");
        }

        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return this.status;
    }

    public void setOrderStatus(OrderStatus status) throws ValidationException {     
        if(status == null) {
            throw new ValidationException("Order's status can't be null.");
        }

        this.status = status;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) throws ValidationException {
        if(date == null) {
            throw new ValidationException("Order's date can't be null.");
        }

        this.date = date;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) throws ValidationException {
        if(totalPrice <= 0) {
            throw new ValidationException("Total price of an order must be greater than zero.");
        }

        this.totalPrice = totalPrice;
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "paymentMethod: " + this.paymentMethod + '\n';
        obj += "status: " + this.status + '\n';
        obj += "date: " + this.date + '\n';
        obj += "totalPrice: " + this.totalPrice + '\n';
        obj += "customer: " + '\n' + this.customer.toString(); 

        return obj;
    }
}
