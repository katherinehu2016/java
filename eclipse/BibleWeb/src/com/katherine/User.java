package com.katherine;

import java.sql.ResultSet;
import java.sql.Statement;

public class User extends DBObject {
	private String username;
	private String password;
	private String userid;
	
	public boolean authenticate (String username, String password){
		boolean result = false;
		openConnection();
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
			ResultSet res = st.executeQuery(sql);
			
			if (res.next() ) {
				this.setUsername(username);
				this.setPassword(password);
				this.setUserid(res.getString("userid"));
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return result;
	}
	
	public String getFont (String username) {
		openConnection();
		String font = null;
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM user WHERE username = '" + username + "'";
			ResultSet res = st.executeQuery(sql);
			
			if (res.next()){
				font = res.getString("font");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
	public void setFont (String username, String font) {
		openConnection();
		try {
			Statement st = con.createStatement();
			String sql = "UPDATE `user` SET `font` = '" + font + "' WHERE `user`.`username` = '" + username + "'";
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getFontSize (String username) {
		openConnection();
		int fontSize = 0;
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM user WHERE username = '" + username + "'";
			ResultSet res = st.executeQuery(sql);
			
			if (res.next()){
				fontSize = res.getInt("fontsize");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fontSize;
	}
	
	public void setFontSize (String username, int fontsize) {
		openConnection();
		try {
			Statement st = con.createStatement();
			String sql = "UPDATE `user` SET `fontsize` = " + fontsize + " WHERE `user`.`username` = '" + username + "'";
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addUser (String username, String password) {
		openConnection();
		int result = 0;
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate("INSERT into user (username, password) VALUES('" + username
					+ "', '" + password + "'");
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		closeConnection();
		return result;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword () {
		return password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUsername (String username) {
		this.username = username;
	}
	public void setPassword (String password) {
		this.password = password;
	}
	public void setUserid (String userid) {
		this.userid = userid;
	}
}
