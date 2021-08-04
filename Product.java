class Product {
    String id;
    String name;
    String category;
    double price;

    public Product(User loggedUser, String mName, String mCategory, double mPrice) {
        try {
            if(loggedUser.type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Insert product's data into database.

            name = mName;
            category = mCategory;
            price = mPrice;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Product findById(String id) {
        //Database's find method to get requested product;

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product findedProduct = new Product(usr1, "product01", "category01", 5.25);

        return findedProduct;
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

    public boolean update(String mName, String mCategory, double mPrice) {
        try {
            //Update product on database.

            name = mName;
            category = mCategory;
            price = mPrice;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public boolean delete(User loggedUser) {
        try {
            if(loggedUser.type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Delete product from database.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
