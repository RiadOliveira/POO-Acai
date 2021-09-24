package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
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
}
