<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.katherine.Books" %>
<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Online Bible!</title>
</head>

<body>

<table border=0>

<tr> 
<td> Books of the Bible </td>
</tr>

<%
	Books books = new Books();
	ResultSet resultSet = books.getBooks();
	// ResultSet is initially before the first data set
	while (resultSet != null && resultSet.next()) {
		// It is possible to get the columns via name
		// also possible to get the columns via the column number
		// which starts at 1
		// e.g. resultSet.getSTring(2);
		String id = resultSet.getString("book_id");
		String name = resultSet.getString("book_name");
		String number = resultSet.getString("book_number");
		
%>

<tr>
	<td><%=name %>
	<% 
	} 
	books.closeConnection();
%>

	 </td>
</tr>

</table>
</body>
</html>