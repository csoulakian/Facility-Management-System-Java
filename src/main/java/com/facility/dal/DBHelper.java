package com.facility.dal;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	@SuppressWarnings("unused")
	public static Connection getConnection() {
		 
		System.out.println("DBHelper: -------- PostgreSQL " + "JDBC Connection  ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("DBHelper: Check Where  your PostgreSQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection("jdbc:postgresql://ec2-107-20-153-141.compute-1.amazonaws.com:5432/d97an3do400poj", "kuqmujecqtqkaq", "sq4wIZ1diEJrcaf6jcmiZXciK1");
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
	            }
 
		} catch (SQLException e) {
 
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		if (connection != null) {
			System.out.println("DBHelper: You have a database connection!");
		} else {
			System.out.println("DBHelper: Failed to make connection!");
		}
		
		return connection;
	}
}

