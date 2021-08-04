class Product {
    String id;
    String name;
    String category;
    double price;

    public Product(User user, String name, String category, double price) {
        try {
            if(user.type != UserType.admin) {
                throw new Exception("The user not has permission to execute this action.");
            }

            //Insert product data on database.
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Product[] findAll() {
        //Database's find method to get all users where type == 0 (customer);

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");

        Product product1 = new Product(usr1, "product01", "category01", 5.25);
        Product product2 = new Product(usr1, "product02", "category02", 4.10);

        Product products[] = {product1, product2};

        return products;
    }
}
