package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import model.VO.ProductVO;
import utils.Category;

public class ProductDAO extends BaseDAO {
    public static void insert(ProductVO product) throws SQLException { //May return Product with id.
        //Insert product into database.
    	Connection connection = getConnection();
		String query = "INSERT INTO PRODUCTS (name, category, price) values (?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, product.getName());
		statement.setInt(2, product.getCategory().ordinal());
		statement.setDouble(3, product.getPrice());

		statement.execute();
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

    public static ProductVO[] findAll() {
        //Database's find method to get all products;

        //To simulate database's return:
        ProductVO product1 = new ProductVO();
        ProductVO product2 = new ProductVO();

        ProductVO products[] = {product1, product2};

        return products;
    }

    public static void update(ProductVO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "UPDATE products SET category=?, price=? WHERE name=?";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
//        statement.setString(1, product.getName());
        statement.setInt(1, product.getCategory().ordinal());
        statement.setDouble(2, product.getPrice());
        statement.setString(3, product.getName());

        statement.execute();
    }

    public static void delete(ProductVO product) throws SQLException {
        //Delete product on database using its id.
    	Connection connection = getConnection();
    	
    	String query = "DELETE FROM products WHERE name=?";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, product.getName());
        statement.execute();
    }
}
