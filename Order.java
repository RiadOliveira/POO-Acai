import java.time.LocalDate;

class Order {
    User customer;
    String productName;
    PaymentMethods paymentMethod;
    OrderStatus status;
    LocalDate date;
    int quantity;
}
