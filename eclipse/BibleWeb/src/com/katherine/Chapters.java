package com.katherine;

import java.sql.ResultSet;
import java.sql.Statement;

public class Chapters extends DBObject {
	
	public ResultSet getChapters(int id) {
		ResultSet res = null;
		openConnection();
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM chapter WHERE book_id="+ id +" ORDER BY chapter_number";
			res = st.executeQuery(sql);
			
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;		
	}
	
	public int countChapters(int bookid) {
	{
		
		int numOfChap = 0;
		ResultSet rs = null;
		openConnection();
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT COUNT(*) AS numOfChap FROM chapter WHERE book_id=" + bookid;
			rs = st.executeQuery(sql);
			//if (rs != null ){
				rs.next();
				numOfChap = rs.getInt("numOfChap");
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numOfChap;	
		
	}
}}

