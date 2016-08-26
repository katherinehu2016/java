<%@ page import="com.katherine.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String errorMsg = "";

//Hint: The username and password are both "t"

if (session.getAttribute("username")!= null) {
	response.sendRedirect("index.jsp");
}

if (request.getParameter("action")!= null && request.getParameter("action").equals("LOGIN")) {
	String username = request.getParameter("txtUsername");
	String password = request.getParameter("txtPassword");
	
	User user = new User();
	
	boolean isValid = user.authenticate(username, password);
	
	if (isValid){
		session.setAttribute("username", username);
		session.setAttribute("userid", user.getUserid());
		response.sendRedirect("index.jsp");
	} else {
		errorMsg = "Sorry, your username and password are invalid. Please try again!";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4.loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
		<script language="javascript">
			function load(){
				document.forms["frmLogin"]["txtUsername"].focus();
			}
		</script>
</head>
<body onload="javascript:load();">
<form id="frmLogin" action="login.jsp" method="post">
	<table align="center" cellpadding="0" cellspacing="0" border="0" width="90%">
		<tr>
			<td align="right"><font face="Georgia"><b>Username:</b></font></td>
			<td width="10">&nbsp;</td>
			<td><input type="text" id="txtUsername" name="txtUsername" value=""/></td>
		</tr>
		<tr>
			<td align="right"><font face="Georgia"><b>Password:</b></font></td>
			<td>&nbsp;</td>
			<td> <input type="password" id="txtPassword" name="txtPassword" value=""></td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;</td>
			<td>
				<input type="submit" id="btnLogin" value="Login">
				<input type="hidden" id="action" name="action" value="LOGIN"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;</td>
			<td>
				<font face="Georgia" color="red"><%=errorMsg %></font>
			</td>
		</tr>
	</table>
</form>
</body>
</html>