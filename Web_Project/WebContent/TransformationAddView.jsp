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
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center">Transformation Add</h1>

	<div align="center">
		<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
		<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>

		<form action="TransformationAddCtl" method="post">
			<table>
				<tr>
					<th>Transformation Code:</th>
					<td><input type="text" name="code" placeholder="Enter Code"
						value=""></td>
				</tr>
				<tr>
					<th>Transformation Name:</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value=""></td>
				</tr>
				<tr>
					<th>Logic:</th>
					<td><input type="text" name="logic" placeholder="Enter Logic"
						value=""></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><input type="text" name="status"
						placeholder="Enter Status" value=""></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="save"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>