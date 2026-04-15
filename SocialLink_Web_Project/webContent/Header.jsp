
<%@page import="com.rays.bean.SocialLinkBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	SocialLinkBean socialBean = (SocialLinkBean) session.getAttribute("socialLink");
	%>


	<h1>Hii</h1>
	<a href="AddUserCtl">Add Social Link</a> |
	<a href="SocialLinkListCtl">Social Link List</a> |


	<a href="WelcomeCtl">Welcome</a>
	<hr>


</body>
</html>
