package src.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	private static Connection connection = null;
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/poo_acai";
		String user = "postgres";
		String password = "46194673@";

		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		} else {
			return connection;
		}
	}
}
