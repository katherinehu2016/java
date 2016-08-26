package com.katherine;

import java.sql.ResultSet;
import java.sql.Statement;

public class Verses extends DBObject {
	
	public ResultSet getVerses(int book, int chapter) {
		ResultSet res = null;
		openConnection();
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM verse WHERE book_number="+ book + " AND chapter_number=" + chapter +" ORDER BY verse_number";
			res = st.executeQuery(sql);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;		
	}
	
	
}
