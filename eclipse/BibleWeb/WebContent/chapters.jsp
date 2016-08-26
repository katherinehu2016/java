<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.katherine.Chapters" %>
<%
	int bookNumber = Integer.parseInt(request.getParameter("bookNum"));
	String bookName = request.getParameter("book");
	Chapters chapters = new Chapters();
	ResultSet resultSet = chapters.getChapters(bookNumber);
	String fontPref = (String)session.getAttribute("fontPref");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose a Chapter</title>
<style type="text/css">
SPAN {font:<%=fontPref%>}
</style>
</head>
<body link=blue vlink=blue>
<table border=0>
<tr>
<td><span>Chapters in <%=bookName %></span></td>
<td><a href='index.jsp'><span>Choose another book</span></a></td>
<td><span><a href='settings.jsp?from=chapters&bookNum=<%=bookNumber%>&book=<%=bookName%>'>
Change Font Settings</a></span></td>
</tr>
</table>

<table border=0>

<%
	// ResultSet is initially before the first data set
	while (resultSet != null && resultSet.next()) {
		// It is possible to get the columns via name
		// also possible to get the columns via the column number
		// which starts at 1
		// e.g. resultSet.getSTring(2);
		String id = resultSet.getString("chapter_id");
		String number = resultSet.getString("chapter_number");
		String book = resultSet.getString("book_id");
%>

<tr>
<td><a href='verses.jsp?chapter=<%=number %>&book=<%=bookName %>&bookNum=<%=bookNumber %>'><span>
<%=number %>
</span></a>
<%
	} 
	chapters.closeConnection();
%>
</td>
</tr>

</table>
</body>
</html>