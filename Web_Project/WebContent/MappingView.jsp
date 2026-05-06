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

	<div align="center">
		<h1 align="center">Add Data Mapping</h1>

		<h3 align="center" style="color: Green"><%=successMsg != null ? successMsg : ""%></h3>
		<h3 align="center" style="color: Red"><%=errorMsg != null ? errorMsg : ""%></h3>

		<form action="MappingCtl" method="post">

			<table>
				<tr>
					<th>Mapping Code:</th>
					<td><input type="text" name="code"
						placeholder="Enter Data Code" value=""></td>
				</tr>
				<tr>
					<th>Source Field:</th>
					<td><input type="text" name="sourceField"
						placeholder="Enter Source Field" value=""></td>
				</tr>
				<tr>
					<th>Target Field:</th>
					<td><input type="text" name="targetField"
						placeholder="Enter Target Field" value=""></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><input type="text" name="status"
						placeholder="Enter Status" value=""></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="Save"></td>
				</tr>
			</table>

		</form>
	</div>

</body>
</html>