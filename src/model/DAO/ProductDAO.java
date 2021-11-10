package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import errors.ValidationException;
import model.VO.ProductVO;

public class ProductDAO<VO extends ProductVO> extends BaseDAO<VO> {
    public void insert(VO product) throws SQLException, ValidationException {
    	Connection connection = getConnection();
		String query = "INSERT INTO products (name, category, price) values (?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, product.getName());
		statement.setInt(2, product.getCategory().ordinal());
		statement.setDouble(3, product.getPrice());

		statement.execute();
		
		ResultSet generatedKeys = statement.getGeneratedKeys();
		
		if(generatedKeys.next()) {
        	product.setId(UUID.fromString(generatedKeys.getString(1)));
        } else {
        	throw new SQLException("User's ID not found on database");
        }
    }
    
    public ResultSet findAll() throws SQLException {
        Connection connection = getConnection();

        String query = "SELECT * FROM products";

        Statement statement = connection.createStatement();
        ResultSet findedProducts = statement.executeQuery(query);

        return findedProducts;
    }

    public ResultSet findById(VO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	 String query = "SELECT * FROM products WHERE id=?::uuid";
    	 
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setObject(1, product.getId());

         ResultSet findedProduct = statement.executeQuery();

         if(!findedProduct.next()) {
             return null;
         }

         return findedProduct;
    }
    
    public void update(VO product) throws SQLException {
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

    public void delete(VO product) throws SQLException {
    	Connection connection = getConnection();
    	
    	String query = "DELETE FROM products WHERE id=?::uuid";

        PreparedStatement statement;

        statement = connection.prepareStatement(query);
        statement.setString(1, product.getId().toString());
        statement.execute();
    }
}
