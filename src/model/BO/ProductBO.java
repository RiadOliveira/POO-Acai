package src.model.BO;

import src.utils.UserType;

import java.util.UUID;

import src.model.DAO.ProductDAO;
import src.model.VO.ProductVO;
import src.model.VO.UserVO;

public class ProductBO {
    public static boolean create(UserVO user, ProductVO product) {
        try {
            if(user.getType() != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            UUID productId = ProductDAO.insert(product);

            product.setId(productId);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean update(ProductVO product) {
        try {
            if(ProductDAO.findById(product) == null) {
                throw new Exception("Product not found.");
            }
            
            ProductDAO.update(product);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean delete(UserVO user, ProductVO product) {
        try {
            if(user.getType() != UserType.admin) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            if(ProductDAO.findById(product) == null) {
                throw new Exception("Product not found.");
            }
            
            ProductDAO.delete(product);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
