package services;

import utilities.PropertiesUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
	private static ConnectionService instance;
	private static Connection connection = null;

	private ConnectionService() {
		connection = makeConnection();
	}

	// Return singleton instance
	public static synchronized ConnectionService getInstance() {
		if (instance == null) {
			instance = new ConnectionService();
		}
		return instance;
	}

	// Make connection
	private Connection makeConnection() {
		Connection c = null;
		Properties p = PropertiesUtility.getProperties();

		try {
			Class.forName(p.getProperty("driver"));
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pass = p.getProperty("password");
			c = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return c;
	}

	// Return connection
	public Connection getConnection() {
		return connection;
	}
}
