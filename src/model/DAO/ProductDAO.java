package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.ProductVO;
import utils.Category;

public class ProductDAO extends BaseDAO {
    public static void insert(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
		String query = "INSERT INTO PRODUCTS (name, category, price) values (?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, product.getName());
		statement.setInt(2, product.getCategory().ordinal());
		statement.setDouble(3, product.getPrice());

		statement.execute();
    }
    
    public static List<ProductVO> findAllProducts() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM products";

        Statement statement;
        ResultSet findedProducts;
        List<ProductVO> products = new ArrayList<ProductVO>();

        statement = connection.createStatement();

        findedProducts = statement.executeQuery(query);
        Category[] category = Category.values();

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

    public static ProductVO findById(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	 String query = "SELECT * FROM products WHERE id=?::uuid";
    	 
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setObject(1, product.getId());

         ResultSet findedProduct = statement.executeQuery();

         if(!findedProduct.next()) {
             return null;
         }

         ProductVO findedProductVO = new ProductVO();
         Category[] category = Category.values();

         findedProductVO.setId(UUID.fromString(findedProduct.getString("id")));
         findedProductVO.setName(findedProduct.getString("name"));
         findedProductVO.setCategory(category[findedProduct.getInt("category")]);
         findedProductVO.setPrice(findedProduct.getDouble("price"));
        
         return findedProductVO;
    }
    
    public static ProductVO findByName(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "SELECT * FROM products WHERE name=?";
    	
    	PreparedStatement statement = connection.prepareStatement(query);
    	statement.setString(1, product.getName());
    	
    	ResultSet findedProduct = statement.executeQuery();
    	
    	if(!findedProduct.next()) {
    		return null;
    	}
    	
    	ProductVO findedProductVO = new ProductVO();
    	Category[] category = Category.values();
    	
    	findedProductVO.setId(UUID.fromString(findedProduct.getString("id")));
        findedProductVO.setName(findedProduct.getString("name"));
        findedProductVO.setCategory(category[findedProduct.getInt("category")]);
        findedProductVO.setPrice(findedProduct.getDouble("price"));
    
        return findedProductVO;
    }

    public static void update(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "UPDATE products SET name=?, category=?, price=? WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getCategory().ordinal());
        statement.setDouble(3, product.getPrice());
        statement.setString(4, product.getId().toString());

        statement.execute();
    }

    public static void delete(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "DELETE FROM products WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, product.getId().toString());
        statement.execute();
    }
}
