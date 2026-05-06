<%@page import="com.rays.bean.TransformationBean"%>
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
	TransformationBean bean = (TransformationBean) request.getAttribute("bean");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center"><%=bean != null ? "Transformation Update" : "Transformation Add"%></h1>

	<div align="center">
		<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
		<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>

		<form action="TransformationAddCtl" method="post">
			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getId() : ""%>">
			<table>
				<tr>
					<th>Transformation Code:</th>
					<td><input type="text" name="code" placeholder="Enter Code"
						value="<%=bean != null ? bean.getCode() : ""%>"></td>
				</tr>
				<tr>
					<th>Transformation Name:</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=bean != null ? bean.getName() : ""%>"></td>
				</tr>
				<tr>
					<th>Logic:</th>
					<td><input type="text" name="logic" placeholder="Enter Logic"
						value="<%=bean != null ? bean.getLogic() : ""%>"></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><input type="text" name="status"
						placeholder="Enter Status"
						value="<%=bean != null ? bean.getStatus() : ""%>"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "Update" : "Save"%>"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>