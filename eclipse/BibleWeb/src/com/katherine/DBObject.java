package com.katherine;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBObject {
	Connection con = null;
	
	public void openConnection(){
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "bible";
		String driverName= "com.mysql.jdbc.Driver";
		String userName = "khu";
		String password = "khu";
		
		try {
			Class.forName(driverName).newInstance();
			con = DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection () {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
