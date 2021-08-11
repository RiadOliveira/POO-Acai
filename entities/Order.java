package entities;

import java.time.LocalDate;

import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportType;
import utils.OrderProduct;

public class Order {
    String id;
    OrderProduct[] orderProducts;
    String customerId;
    PaymentMethod paymentMethod;
    OrderStatus status;
    LocalDate date;
    double totalPrice;

    public Order(
        String customerId, OrderProduct orderProducts[], 
        PaymentMethod paymentMethod, LocalDate date, double totalPrice
    ) {
        try {
            if(User.findById(customerId) == null) {
                throw new Exception("Requested customer does not exist.");
            }

            //Insert order's data into database.

            //Needs to insert id of Order.
            this.customerId = customerId;
            this.orderProducts = orderProducts;
            this.paymentMethod = paymentMethod;
            status = OrderStatus.analyzing;
            this.date = date;
            this.totalPrice = totalPrice;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Order findById(String id) {
        //Database's find method to get requested order.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        OrderProduct product1 = new OrderProduct("orderId","productId", 5);
        LocalDate date = LocalDate.now();

        OrderProduct products[] = {product1};

        Order order1 = new Order(usr1.id, products, PaymentMethod.Card, date, 10);

        return order1;
    }

    public static Order[] findByCustomer(String customerId) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        OrderProduct product1 = new OrderProduct("order1Id","product1Id", 5);
        OrderProduct product2 = new OrderProduct("order2Id","product2Id", 5);

        LocalDate date = LocalDate.now();

        OrderProduct products[] = {product1, product2};

        Order order1 = new Order(usr1.id, products, PaymentMethod.Card, date, 10);
        Order order2 = new Order(usr1.id, products, PaymentMethod.Money, date, 2);

        Order orders[] = {order1, order2};

        return orders;
    }

    public static Order[] findByProduct(String productId) {
        //Uses database's find method to get all orders of that product.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        OrderProduct product1 = new OrderProduct("order1Id","product1Id", 5);
        OrderProduct product2 = new OrderProduct("order2Id","product2Id", 5);

        LocalDate date = LocalDate.now();

        OrderProduct products[] = {product1, product2};

        Order order1 = new Order(usr1.id, products, PaymentMethod.Card, date, 10);
        Order order2 = new Order(usr1.id, products, PaymentMethod.Money, date, 2);

        Order orders[] = {order1, order2};

        return orders;
    }

    public static Order[] findByDate(Order allOrders[], int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);

        int searchedOrdersLength = 0;
        int searchedPositions[] = new int[allOrders.length];

        for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
            if(allOrders[ind].date.compareTo(date) == 0) {
                searchedOrdersLength++;
                searchedPositions[i++] = ind;
            }
        }

        Order searchedOrders[] = new Order[searchedOrdersLength];

        for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
            searchedOrders[ind] = allOrders[searchedPositions[ind]];
        }

        return searchedOrders;
    }

    public static Order[] generateReport(
        Order allOrders[], ReportType type, 
        int day, int month, int year
    ) {
        switch(type) { 
            case day: return Order.findByDate(allOrders, day, month, year);
            
            case week: {
                int startOfWeek = day - LocalDate.of(year, month, day).getDayOfWeek().getValue();

                LocalDate initialDate = LocalDate.of(year, month, startOfWeek);
                LocalDate finalDate = LocalDate.of(year, month, startOfWeek).plusDays(7);

                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.length];

                for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
                    if(
                        allOrders[ind].date.compareTo(initialDate) >= 0 &&
                        allOrders[ind].date.compareTo(finalDate) < 0
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                Order searchedOrders[] = new Order[searchedOrdersLength];

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders[ind] = allOrders[searchedPositions[ind]];
                }

                return searchedOrders;
            }
            
            case month: {
                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.length];

                for(int ind=0, i=0 ; ind<allOrders.length ; ind++) {
                    if(
                        allOrders[ind].date.getYear() == year &&
                        allOrders[ind].date.getMonthValue() == month
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                Order searchedOrders[] = new Order[searchedOrdersLength];

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders[ind] = allOrders[searchedPositions[ind]];
                }

                return searchedOrders;
            }

            default: return null;
        }
    }

    public boolean update(
        String customerId, OrderProduct orderProducts[], PaymentMethod paymentMethod, 
        OrderStatus status, LocalDate date, double totalPrice
    ) {
        try {
            if(User.findById(customerId) == null) {
                throw new Exception("Requested customer does not exist.");
            }

            //Update order on database.

            this.customerId = customerId;
            this.orderProducts = orderProducts;
            this.paymentMethod = paymentMethod;
            this.status = status;
            this.date = date;
            this.totalPrice = totalPrice;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean delete() {
        try {
            //Delete order on database.

            //After that, on main class, needs to delete this object on order's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
