package model.BO;

import utils.UserType;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Category;

public class ProductBO {
    public static boolean create(UserType loggedUserType, ProductVO product, String name, Category category, double price) {
        try {
            if(loggedUserType != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            //Insert product's data into database.

            //Needs to insert id of Product.

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static ProductVO findById(String id) {
        //Database's find method to get requested product;

        //To simulate database's return:
        UserVO user = new UserVO();
        UserBO.signUp(user, "name1", "cpf1", "password1", "phoneNumber1", UserType.admin);

        ProductVO findedProduct = new ProductVO();

        ProductBO.create(user.getType(), findedProduct, "product1", Category.acai, 5.25);

        return findedProduct;
    }

    public static ProductVO[] findAll() {
        //Database's find method to get all products;

        //To simulate database's return:
        UserVO user = new UserVO();
        UserBO.signUp(user, "name1", "cpf1", "password1", "phoneNumber1", UserType.admin);

        ProductVO product1 = new ProductVO();
        ProductVO product2 = new ProductVO();

        ProductBO.create(user.getType(), product1, "product1", Category.acai, 5.25);
        ProductBO.create(user.getType(), product2, "product2", Category.pizza, 4.10);

        ProductVO products[] = {product1, product2};

        return products;
    }

    public static boolean update(ProductVO product, String name, Category category, double price) {
        try {
            //Update product on database.

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean delete(UserType loggedUserType, ProductVO product) { //Verify if can pass only product's id.
        try {
            if(loggedUserType != UserType.admin) {
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
