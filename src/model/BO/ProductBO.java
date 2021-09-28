package model.BO;

import model.DAO.ProductDAO;
import model.VO.ProductVO;
import model.VO.UserVO;

public class ProductBO {
	private static ProductDAO productDAO = new ProductDAO();
	
    public static boolean create(UserVO user, ProductVO product) {
        try {
            if(!user.getIsAdmin()) {
                throw new Exception("The user does not have permission to execute this action.");
            }

            productDAO.insert(product);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }

    public static boolean update(ProductVO product) {
        try {
            if(productDAO.findById(product) == null) {
                throw new Exception("Product not found.");
            }
            
            productDAO.update(product);

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
            
            productDAO.delete(product);

            return true;
        } catch(Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return false;
        }
    }
}
