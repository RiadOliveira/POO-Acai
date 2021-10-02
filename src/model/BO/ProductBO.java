package model.BO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.DAO.ProductDAO;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Category;

public class ProductBO {
	private static ProductDAO<ProductVO> productDAO = new ProductDAO<ProductVO>();
	
    public static boolean insert(UserVO user, ProductVO product) {
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

    public List<ProductVO> findAll() {
        try {
            List<ProductVO> products = new ArrayList<ProductVO>();
            ResultSet findedProducts = productDAO.findAll();

            Category category[] = Category.values();

            while(findedProducts.next()) {
                ProductVO product = new ProductVO();
                
                product.setId(UUID.fromString(findedProducts.getString("id")));
                product.setName(findedProducts.getString("name"));
                product.setCategory(category[findedProducts.getInt("category")]);
                product.setPrice(findedProducts.getDouble("price"));
    
                products.add(product);
            }

            return products;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return null;
        }
    }

    public ProductVO findById(ProductVO product) {
    	try{
            ProductVO findedProduct = new ProductVO();
            ResultSet findedProductDB = productDAO.findById(product);

            Category category[] = Category.values();
            
            product.setId(UUID.fromString(findedProductDB.getString("id")));
            product.setName(findedProductDB.getString("name"));
            product.setCategory(category[findedProductDB.getInt("category")]);
            product.setPrice(findedProductDB.getDouble("price"));

            return findedProduct;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());

            return null;
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
