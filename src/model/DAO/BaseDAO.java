package model.DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseDAO<VO> implements BaseInterDAO<VO> {
    private static Connection connection = null;
    private static final String url = "jdbc:postgresql://localhost:5432/poo_acai";
    private static final String user = "postgres";
    private static final String password = "46194673";
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

		return connection;
	}

	public static void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
}
