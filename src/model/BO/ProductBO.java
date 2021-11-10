package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import errors.ValidationException;
import model.DAO.ProductDAO;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Category;

public class ProductBO {
	private static ProductDAO<ProductVO> productDAO = new ProductDAO<ProductVO>();
	
    public static void insert(UserVO user, ProductVO product) throws Exception {
        if(!user.getIsAdmin()) {
            throw new Exception("The user does not have permission to execute this action.");
        }

        productDAO.insert(product);
    }

    public static List<ProductVO> findAll() throws SQLException, ValidationException {
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
    }

    public static List<ProductVO> findByName(List<ProductVO> allProducts, String searchedName) {
        int findedProductsLength = 0;
        int findedProductsPositions[] = new int[allProducts.size()];

        for(int ind=0, i=0 ; ind < allProducts.size() ; ind++) {
            if(
                allProducts.get(ind).getName().toLowerCase().
                contains(searchedName.toLowerCase())
            ) {
                findedProductsLength++;
                findedProductsPositions[i++] = ind;
            }
        }

        List<ProductVO> findedProducts = new ArrayList<ProductVO>();

        for(int ind=0 ; ind<findedProductsLength ; ind++) {
            findedProducts.add(allProducts.get(findedProductsPositions[ind]));
        }

        return findedProducts;
    }

    public static ProductVO findById(ProductVO product) throws SQLException, ValidationException {
            ProductVO findedProduct = new ProductVO();
            ResultSet findedProductDB = productDAO.findById(product);

            Category category[] = Category.values();
            
            findedProduct.setId(UUID.fromString(findedProductDB.getString("id")));
            findedProduct.setName(findedProductDB.getString("name"));
            findedProduct.setCategory(category[findedProductDB.getInt("category")]);
            findedProduct.setPrice(findedProductDB.getDouble("price"));

            return findedProduct;
    }

    public static void update(ProductVO product) throws Exception {
        if(productDAO.findById(product) == null) {
            throw new Exception("Product not found.");
        }
        
        productDAO.update(product);
    }
    
    public static void delete(UserVO user, ProductVO product) throws Exception {
        if(!user.getIsAdmin()) {
            throw new Exception("The user does not have permission to execute this action.");
        }

        if(productDAO.findById(product) == null) {
            throw new Exception("Product not found.");
        }
        
        productDAO.delete(product);
    }
}
