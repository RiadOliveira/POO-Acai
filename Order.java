import java.time.LocalDate;

class Order {
    String id;
    User customer;
    String productName;
    PaymentMethods paymentMethod;
    OrderStatus status;
    LocalDate date;
    int quantity;
}
