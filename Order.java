import java.time.LocalDate;

class Order {
    String id;
    User customer;
    Product product;
    PaymentMethods paymentMethod;
    OrderStatus status;
    LocalDate date;
    int quantity;

    public Order(
        String customerId, String productId, PaymentMethods mPaymentMethod,
        LocalDate mDate, int mQuantity
    ) {
        try {
            User mCustomer = User.findById(customerId);
            Product mProduct = Product.findById(productId);

            if(mCustomer == null) {
                throw new Exception("Requested customer does not exists.");
            }

            //Insert order's data into database.

            customer = mCustomer;
            product = mProduct;
            paymentMethod = mPaymentMethod;
            date = mDate;
            quantity = mQuantity;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Order findById(String id) {
        //Database's find method to get requested order;

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethods.Card, date, 10);

        return order1;
    }

    public static Order[] findByCustomer(String customerId) {
        //Uses database's find method to get all orders of that customer.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethods.Card, date, 10);
        Order order2 = new Order(usr1.id, product1.id, PaymentMethods.Money, date, 2);

        Order orders[] = {order1, order2};

        return orders;
    }

    public static Order[] findByProduct(String productId) {
        //Uses database's find method to get all orders of that product.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        LocalDate date = LocalDate.now();

        Order order1 = new Order(usr1.id, product1.id, PaymentMethods.Card, date, 10);
        Order order2 = new Order(usr1.id, product1.id, PaymentMethods.Money, date, 2);

        Order orders[] = {order1, order2};

        return orders;
    }

    public static Order[] findByDate(int day, int month, int year) {
        //Uses database's find method to get all orders on that date.

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product product1 = new Product(usr1, "product01", "category01", 5.25);

        LocalDate date = LocalDate.now();
        Order order1 = new Order(usr1.id, product1.id, PaymentMethods.Card, date, 10);
        Order order2 = new Order(usr1.id, product1.id, PaymentMethods.Money, date, 2);

        Order orders[] = {order1, order2};

        //Search logic
        int searchedOrdersLength = 0;

        for(int ind=0 ; ind<orders.length ; ind++) {
            if(
                orders[ind].date.getDayOfMonth() == day && 
                orders[ind].date.getMonthValue() == month &&
                orders[ind].date.getYear() == year    
            ) {
                searchedOrdersLength++;
            }
        }

        Order searchedOrders[] = new Order[searchedOrdersLength];

        for(int ind=0, i=0 ; ind<orders.length ; ind++) {
            if(
                orders[ind].date.getDayOfMonth() == day && 
                orders[ind].date.getMonthValue() == month &&
                orders[ind].date.getYear() == year    
            ) {
                searchedOrders[i++] = orders[ind];
            }
        }

        return searchedOrders;
    }
}
