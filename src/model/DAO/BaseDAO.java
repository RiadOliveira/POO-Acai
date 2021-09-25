package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO<Entity> implements IBaseDAO<Entity> {
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			String url = "jdbc:postgresql://localhost:5432/poo_acai";
			String user = "postgres";
			String password = "46194673";

			connection = DriverManager.getConnection(url, user, password);
		}

		return connection;
	}

	public static void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}

	public abstract void insert(Entity entity) throws SQLException;
    public abstract ResultSet findById(Entity entity) throws SQLException;
    public abstract void update(Entity entity) throws SQLException;
    public abstract void delete(Entity entity) throws SQLException;
}
