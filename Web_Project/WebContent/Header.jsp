
<%@page import="com.rays.bean.UserBean"%>
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
	UserBean userBean = (UserBean) session.getAttribute("user");
	%>

	<%
	if (userBean != null) {
	%>
	<h1><%="Hii, " + userBean.getFirstName()%></h1>
	<a href="UserCtl.do">Add User</a> |
	<a href="UserListCtl.do">User List</a> |
	<a href="LoginCtl?operation=logout">Logout</a> |
	<%
	} else {
	%>

	<h3>Hii, Guest</h3>
	<a href="LoginCtl">Login</a> |
	<a href="UserRegistrationCtl">Sign Up</a> |
	<a href="JobAddCtl">Job Add</a> |
	<a href="JobListCtl">Job List</a> |
	<a href="TransformationAddCtl">Transformation Add</a> |
	<a href="TransformationListCtl">Transformation List</a> |
	<a href="MappingCtl">Data Mapping</a> |
	<a href="MappingListCtl">Data Mapping List</a> |
	<a href="ParkingCtl">Add Smart Parking</a> |
	<a href="ParkingListCtl">Smart Parking List</a> |
	<%
	}
	%>
	<a href="WelcomeCtl">Welcome</a>
	<hr>


</body>
</html>
