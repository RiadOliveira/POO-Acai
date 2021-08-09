import java.time.LocalDate;

import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportTypes;

class Order {
    String id;
    String customerId;
    String productId;
    PaymentMethod paymentMethod;
    OrderStatus status;
    LocalDate date;
    int quantity;

    public Order(
        String mCustomerId, String mProductId, PaymentMethod mPaymentMethod,
        LocalDate mDate, int mQuantity
    ) {
        try {
            if(User.findById(customerId) == null) {
                throw new Exception("Requested customer does not exists.");
            }

            if(Product.findById(productId) == null) {
                throw new Exception("Requested product does not exists.");
            }

            //Insert order's data into database.

            customerId = mCustomerId;
            productId = mProductId;
            paymentMethod = mPaymentMethod;
            status = OrderStatus.analyzing;
            date = mDate;
            quantity = mQuantity;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Order findById(String id) {
        //Database's find method to get requested order.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethod.Card, date, 10);

        return order1;
    }

    public static Order[] findByCustomer(String customerId) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethod.Card, date, 10);
        Order order2 = new Order(usr1.id, product1.id, PaymentMethod.Money, date, 2);

        Order orders[] = {order1, order2};

        return orders;
    }

    public static Order[] findByProduct(String productId) {
        //Uses database's find method to get all orders of that product.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethod.Card, date, 10);
        Order order2 = new Order(usr1.id, product1.id, PaymentMethod.Money, date, 2);

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
        Order allOrders[], ReportTypes type, 
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
        String mCustomerId, String mProductId, PaymentMethod mPaymentMethod, 
        OrderStatus mStatus, LocalDate mDate, int mQuantity
    ) {
        try {
            if(User.findById(customerId) == null) {
                throw new Exception("Requested customer does not exists.");
            }

            if(Product.findById(productId) == null) {
                throw new Exception("Requested product does not exists.");
            }

            //Update order on database.

            customerId = mCustomerId;
            productId = mProductId;
            paymentMethod = mPaymentMethod;
            status = mStatus;
            date = mDate;
            quantity = mQuantity;

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
