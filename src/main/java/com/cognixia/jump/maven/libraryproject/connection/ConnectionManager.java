package com.cognixia.jump.maven.libraryproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// create resources folder in src/main
// right click on project Build Path -> Configure Build Path -> Source -> Add Folder
// then make sure resources folder is checked then hit Apply and Close
// now create the config.properties file with all the database connection info
public class ConnectionManager {
	
	private static Connection connection = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/crud_library_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	private static void makeConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		
		if(connection == null) {
			makeConnection();
		}
		
		return connection;
	}

}
