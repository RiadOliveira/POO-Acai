package model.BO;

import utils.UserType;
import model.DAO.ProductDAO;
import model.VO.ProductVO;
import utils.Category;

public class ProductBO {
    public static boolean create(
        UserType loggedUserType, ProductVO product, String name, 
        Category category, double price
    ) {
        try {
            if(loggedUserType != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            String productId = ProductDAO.insert(name, category, price);

            product.setId(productId);
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean update(
        ProductVO product, String name, Category category, double price
    ) {
        try {
            if(ProductDAO.findById(product.getId()) == null) {
                throw new Exception("Product not found.");
            }
            
            ProductDAO.update(product.getId(), name, category, price);

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }

    public static boolean delete(UserType loggedUserType, ProductVO product) {
        try {
            if(loggedUserType != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            if(ProductDAO.findById(product.getId()) == null) {
                throw new Exception("Product not found.");
            }
            
            ProductDAO.delete(product.getId());
            product = null;

            return true;
        } catch(Exception err) {
            //Handle the exception.

            return false;
        }
    }
}
