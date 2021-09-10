package model.BO;

import model.DAO.ProductDAO;
import model.VO.ProductVO;
import model.VO.UserVO;

public class ProductBO {
    public static boolean create(UserVO user, ProductVO product) {
        try {
            if(!user.getIsAdmin()) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            ProductDAO.insert(product);

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
            if(!user.getIsAdmin()) {
                throw new Exception("The user does not have permission to execute this action.");
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
