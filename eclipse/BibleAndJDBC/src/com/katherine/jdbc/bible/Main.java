package com.katherine.jdbc.bible;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;


public class Main {
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bible?";
	private static final String DB_USER = "khu";
	private static final String DB_PASSWORD = "khu";
	
	static int verseNum;
	static String verse;
	static int chapter;


    public static void main(String[] args) throws Exception {

    	Main hello = new Main();
		try {
			hello.readDataBase();

		} catch (Exception e){
			
		}
    	
        int folderLength = new File("files").listFiles().length;
        folderLength = folderLength + 62;
        int bookNum;

        for (int i = 62; i < folderLength; i++) {
        	
        	int lineNum = 0;

            bookNum = i + 1;
            ArrayList bookContents = new ArrayList();

            File fCaption = new File("files/" + bookNum + ".txt");

            getContents(bookContents, fCaption);

            //Loop through the ArrayList and reformat the content
            for (int j = 0; j < bookContents.size(); j++) {
                String line = (String)bookContents.get(j);
                if (line.startsWith("[")) {
                    lineNum ++;
                    if (lineNum < 10) {
                        line = line.substring(3).trim();
                    } else if (lineNum > 10 || lineNum == 10) {
                        line = line.substring(4).trim();
                    }
                    verseNum = lineNum;
                    verse = line;
                    chapter = bookNum;
                    try {
            			hello.addToTable();
            		} catch (Exception e){
            		}
                } else {
                }
            }
        }
        System.out.print("Done!");


    }

    public static void writeFile(String content, String filename) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(filename));

            out.print(content);
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
        }
    }

    /*
     * Get each line from the text file and add it to the ArrayList passed in.
     */

    public static ArrayList getContents(ArrayList lines, File aFile) {
        StringBuilder contents = new StringBuilder();
        if (lines == null) {
            lines = new ArrayList();
        }

        try {
            // use buffering, reading one line at a time
            // FileReader always assumes default encoding is OK!
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            try {
                String line = null; // not declared within while loop

                while ((line = input.readLine()) != null) {
                    lines.add(line);
                }
            } finally {
                input.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lines;
    }

public void readDataBase() throws Exception {
	try {
		// Setup the connection with the DB
		connect = getDBConnection();

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement.executeQuery("select * from verse");
		writeResultSet(resultSet);


	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw e;
	} finally {
		close();
	}

}

private void addToTable() throws Exception {
	Connection dbConnection = null;
	PreparedStatement preparedStatement = null;

	String deleteSQL = "INSERT INTO `verse` (verse_number, verse_content, chapter_id) VALUES (?, ?, ?);";

	try {
		dbConnection = getDBConnection();
		preparedStatement = dbConnection.prepareStatement(deleteSQL);
		preparedStatement.setInt(1, verseNum);
		preparedStatement.setString(2, verse);
		preparedStatement.setInt(3, chapter);
		

		// execute delete SQL statement
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
		String id = resultSet.getString("verse_id");
		String name = resultSet.getString("verse_number");
		String number = resultSet.getString("verse_content");
		String cId = resultSet.getString("chapter_id");
		System.out.println("id: " + id);
		System.out.println("name: " + name);
		System.out.println("number: " + number);
		System.out.println("chapter: " + cId);
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
}}
