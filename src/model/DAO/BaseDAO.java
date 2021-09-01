package src.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	Connection connection = null;
	String url = "jdbc:postgresql://localhost:5432/poo_acai";
	String user = "postgres";
	String senha = "46194673@";
	
	public Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, senha);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		} else {
			return connection;
		}
	}
}
