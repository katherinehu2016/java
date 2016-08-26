<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.katherine.Verses" %>
<%@ page import="com.katherine.Chapters" %>
<%	String fontPref = (String)session.getAttribute("fontPref");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read the Bible</title>
<style type="text/css">
SPAN {font:<%=fontPref%>}
</style>
</head>
<body link=blue vlink=blue>

<%
	String lastChap = null;
	String nextChap = "";
	String hrefLast = null;
	String hrefNext = null;
	int bookNumber = Integer.parseInt(request.getParameter("bookNum"));
	int chapNum = Integer.parseInt(request.getParameter("chapter"));
	String bookName = request.getParameter("book");
	Verses verses = new Verses();
	Chapters chapters = new Chapters();
	ResultSet resultSet = verses.getVerses(bookNumber,chapNum);
	// ResultSet is initially before the first data set
%>

<table border=0>
<tr>
<td><span>Chapter <%=chapNum %></span></td>
<td><a href='chapters.jsp?book=<%=bookName %>&bookNum=<%=bookNumber %>'><span>
Choose another chapter in this book
</span></a></td>
<td><span><a href='settings.jsp?from=verses&bookNum=<%=bookNumber%>&book=<%=bookName%>&chapter=<%=chapNum%>'>
Change Font Settings</a></span></td>
</tr>
<%
	int numOfChap = chapters.countChapters(bookNumber);

	if (chapNum > 1){
		lastChap = "Last Chapter";
		hrefLast = "verses.jsp?chapter=" + (chapNum - 1) + "&book=" + bookName + "&bookNum=" + bookNumber;
	} else {
		lastChap = "";
		hrefLast = null;
	}
	if (chapNum < numOfChap){
		nextChap = "Next Chapter";
		hrefNext = "verses.jsp?chapter=" + (chapNum + 1) + "&book=" + bookName + "&bookNum=" + bookNumber;
	} else {
		nextChap = "";
		hrefNext = null;
	}
%>
<tr>
<td><a href=<%=hrefLast %>><span>
<%=lastChap %>
</span></a></td>
<td><a href=<%=hrefNext %>><span>
<%=nextChap %>
</span></a></td>
</tr>
</table>

<table border=0>
<% 		while (resultSet != null && resultSet.next()) {
		// It is possible to get the columns via name
		// also possible to get the columns via the column number
		// which starts at 1
		// e.g. resultSet.getSTring(2);
		String id = resultSet.getString("verse_id");
		String number = resultSet.getString("verse_number");
		String chapter = resultSet.getString("chapter_number");
		String contents = resultSet.getString("verse_content");%>

<tr>
<td><span><%=number %></span></td>
<td><span><%=contents %></span></td>
<%
	} 
	verses.closeConnection();
%>
</tr>

</table>
</body>
</html>