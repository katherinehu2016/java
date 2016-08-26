<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.katherine.Books" %>
<%@ page import="com.katherine.User" %>
<%@ page import="java.sql.ResultSet" %>
<%	String username = (String)session.getAttribute("username");
	User user = new User();
	String fontPref = (String)session.getAttribute("fontPref");
	if (session.getAttribute("fontPref") == null){
		String font = user.getFont(username);
		int fontSize = user.getFontSize(username);
		fontPref = fontSize + "pt " + font;
		session.setAttribute("fontPref", fontPref);
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the Online Bible!</title>
<style type="text/css">
SPAN {font:<%=fontPref%>}
</style>
</head>

<body link=blue vlink=blue>
<table border=0>
<tr> 
<td><span>Books of the Bible </span></td>
<td><span><a href='settings.jsp?from=index'>Change Font Settings</a></span></td>
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
	<td><a href='chapters.jsp?book=<%=name %>&bookNum=<%=number %>'><span>
	<%=name %>
	</span></a>
	
	<%
	} 
	books.closeConnection();
%>

	 </td>
</tr>

</table>
</body>
</html>