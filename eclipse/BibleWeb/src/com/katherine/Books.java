package com.katherine;

import java.sql.ResultSet;
import java.sql.Statement;

public class Books extends DBObject {
	
	public ResultSet getBooks() {
		ResultSet res = null;
		openConnection();
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM book ORDER BY book_number";
			res = st.executeQuery(sql);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;		
	}
	
	
}
