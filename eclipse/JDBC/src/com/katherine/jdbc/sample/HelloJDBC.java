package com.katherine.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bible?";
	private static final String DB_USER = "khu";
	private static final String DB_PASSWORD = "khu";


	public  static void main(String args[]) throws Exception{
		HelloJDBC hello = new HelloJDBC();
		try {
			hello.readDataBase();

		} catch (Exception e){
			
		}
		try {

			hello.deleteFromTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} /*try {

			hello.addToTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}*/ try {

			hello.updateTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}
	
	
	/*public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/bible?" + "user=khu&password=khu");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from book");
			writeResultSet(resultSet);


		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			close();
		}

	}*/
	
	public void readDataBase() throws Exception {
		try {
			// Setup the connection with the DB
			connect = getDBConnection();

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from book");
			writeResultSet(resultSet);


		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			close();
		}

	}
	
	private void deleteFromTable() throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE from book where book_id = ?;";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, 9);

			// execute delete SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is deleted!");
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private void addToTable() throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "INSERT INTO `book` (book_name, book_number) VALUES (?, ?);";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(2, 7);
			preparedStatement.setString(1, "Judgies");
			

			// execute delete SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is added!");
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private void updateTable() throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "UPDATE book "
				+ " \nSET "
				+ "\nbook_name = ? "
				+ " \nWHERE book_id = ? ;";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, "Judges");
			preparedStatement.setInt(2, 8);



			// execute delete SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is updated!");
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}


	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String id = resultSet.getString("book_id");
			String name = resultSet.getString("book_name");
			String number = resultSet.getString("book_number");
			System.out.println("id: " + id);
			System.out.println("name: " + name);
			System.out.println("number: " + number);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	
}