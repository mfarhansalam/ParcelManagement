package database;

import java.sql.*;

public class DatabaseConnection {
	static Connection connect;
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/project_java";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			try {
				connect = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				System.out.println("Connection Success");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
}