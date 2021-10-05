package model.VO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import utils.OrderStatus;
import utils.PaymentMethod;

public class OrderVO {
    private UUID id;
    private List<OrderProductVO> orderProducts;
    private CustomerVO customer;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private LocalDate date;
    private LocalTime time;
    private double totalPrice;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        try {
            if(id == null) { //UUID can't be created from empty string.
                throw new Exception("Order's id can't be null.");
            }
    
            this.id = id;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public List<OrderProductVO> getOrderProducts() {
        return this.orderProducts;
    }

    public void setOrderProducts(List<OrderProductVO> orderProducts) {
        try {
            for(int ind=0 ; ind<orderProducts.size() ; ind++) {
                if(orderProducts.get(ind) == null) {
                    throw new Exception("None order's product can be null.");
                }
            }
    
            this.orderProducts = orderProducts;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        }
    }

    public CustomerVO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerVO customer) {
        try {
            if(customer == null) {
                throw new Exception("Order's customer can't be null.");
            }
    
            this.customer = customer;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        try {
            if(paymentMethod == null) {
                throw new Exception("Order's payment method can't be null.");
            }
    
            this.paymentMethod = paymentMethod;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public OrderStatus getOrderStatus() {
        return this.status;
    }

    public void setOrderStatus(OrderStatus status) {        
        try {
            if(status == null) {
                throw new Exception("Order's status can't be null.");
            }
    
            this.status = status;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        try {
            if(date == null) {
                throw new Exception("Order's date can't be null.");
            }

            if(date.compareTo(LocalDate.now()) < 0) {
                throw new Exception("An order can't be registered in a past date.");
            }
    
            this.date = date;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }
    
    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        try {
            if(time == null) {
                throw new Exception("Order's date can't be null.");
            }

            if(time.compareTo(LocalTime.now()) < 0) {
                throw new Exception("An order can't be registered in a past date.");
            }
    
            this.time = time;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        try {
            if(totalPrice <= 0) {
                throw new Exception("Total price of an order must be greater than zero.");
            }

            this.totalPrice = totalPrice;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        }
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "orderProducts: " + this.orderProducts + '\n';
        obj += "customer: " + this.customer.toString() + '\n';
        obj += "paymentMethod: " + this.paymentMethod + '\n';
        obj += "status: " + this.status + '\n';
        obj += "date: " + this.date + '\n';
        obj += "totalPrice: " + this.totalPrice;

        return obj;
    }
}
