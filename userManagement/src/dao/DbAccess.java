package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/userManagement";
	private static final String DB_USER = "sa";
	private static final String DB_PWD = "";
	
	private Connection connection = null;
	
	public Connection getConnection() {
		return this.connection;
	}
	
	/** DB接続用のメソッド */
	public void connect() {
		try {
			this.connection = 
				DriverManager.getConnection
					(DB_URL, DB_USER, DB_PWD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** DB切断用のメソッド */
	public void disconnect() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.connection = null;
		}
		
	}
	
}
