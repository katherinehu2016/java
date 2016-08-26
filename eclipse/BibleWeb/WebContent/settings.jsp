<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.katherine.User" %>
<%	User user = new User();
	String username = (String)session.getAttribute("username");
	String fontPref = (String)session.getAttribute("fontPref");
	String font = user.getFont(username);
	int fontSize = user.getFontSize(username);
	
	if (request.getParameter("action")!= null && request.getParameter("action").equals("CHANGEFONT")) {
		font = request.getParameter("fontchoice");
		user.setFont(username,font);
		fontPref = fontSize + "pt " + font;
	} else if (request.getParameter("action")!= null && request.getParameter("action").equals("CHANGESIZE")) {
		fontSize = Integer.parseInt(request.getParameter("sizechoice"));
		user.setFontSize(username, fontSize);
		fontPref = fontSize + "pt " + font;
	}
	session.setAttribute("fontPref", fontPref);
	
	String href = (String)session.getAttribute("href");
	int bookNum = 0;
	int chapter = 0;
	String book = null;
	if (request.getParameter("from")!=null && request.getParameter("from").equals("index")) {
		href = "index.jsp";
	} else if (request.getParameter("from")!=null && request.getParameter("from").equals("chapters")) {
		bookNum = Integer.parseInt(request.getParameter("bookNum"));
		book = (String)request.getParameter("book");
		href = "chapters.jsp?bookNum=" + bookNum + "&book=" + book;
	} else if (request.getParameter("from")!=null && request.getParameter("from").equals("verses")) {
		bookNum = Integer.parseInt(request.getParameter("bookNum"));
		book = (String)request.getParameter("book");
		chapter = Integer.parseInt(request.getParameter("chapter"));
		href = "verses.jsp?bookNum=" + bookNum + "&book=" + book + "&chapter=" + chapter;
	}
	session.setAttribute("href", href);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Font Settings</title>
<style type="text/css">
SPAN {font:<%=fontPref%>}
</style>
</head>
<body>

<table border=0>
<tr>
<td><b><span>Change Font Settings</span></b></td>
<td><span><a href='<%=href %>'>Back</a></span></td>
</tr>
</table>

<span>Change Font</span> <br>
<form>
<input type=radio name=fontchoice value=Georgia>Georgia<br>
<input type=radio name=fontchoice value=Arial>Arial <br>
<input type=radio name=fontchoice value=Verdana>Verdana<br>
<input type=submit name=fontchoice value="Change">
<input type="hidden" id="action" name="action" value="CHANGEFONT"/>
</form>
<br>

<span>Change Font Size</span> <br>
<form>
<input type=radio name=sizechoice value=12><span>12 pt</span><br>
<input type=radio name=sizechoice value=16><span>16 pt</span><br>
<input type=radio name=sizechoice value=20><span>20 pt</span><br>
<input type=submit name=sizechoice value="Change">
<input type="hidden" id="action" name="action" value="CHANGESIZE"/>
</form>

</body>
</html>