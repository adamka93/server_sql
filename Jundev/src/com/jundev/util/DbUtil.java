package com.jundev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {

	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null)
			return connection;
		else {
			try {
				String driver = "com.mysql.jdbc.Driver"; // nem biztos h ez kell nekünk
				String url = null;// TO-DO
				String user = null;// TO-DO
				String password = null;// TO-DO
				/*
				 * pl:
				 * url=jdbc:mysql://localhost:3306/UserDB
				 * user=admin
				 * password=test
				 */
				
				
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}
}
