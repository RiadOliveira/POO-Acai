package entities;

import utils.UserType;
import utils.Category;

public class Product {
    String id;
    String name;
    Category category;
    double price;

    public Product(User loggedUser, String name, Category category, double price) {
        try {
            if(loggedUser.type != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Insert product's data into database.

            //Needs to insert id of Product.
            this.name = name;
            this.category = category;
            this.price = price;
        } catch(Exception err) {
            //Handle the exception.
        }
    }

    public static Product findById(String id) {
        //Database's find method to get requested product;

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");
        Product findedProduct = new Product(usr1, "product01", Category.acai, 5.25);

        return findedProduct;
    }

    public static Product[] findAll() {
        //Database's find method to get all products;

        //To simulate database's return:
        User usr1 = new User("cpf01", "password01");

        Product product1 = new Product(usr1, "product01", Category.acai, 5.25);
        Product product2 = new Product(usr1, "product02", Category.pizza, 4.10);

        Product products[] = {product1, product2};

        return products;
    }

    public boolean update(String name, Category category, double price) {
        try {
            //Update product on database.

            this.name = name;
            this.category = category;
            this.price = price;

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

            //Delete product on database.

            //After that, on main class, needs to delete this object on product's array.

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
