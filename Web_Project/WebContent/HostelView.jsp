<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hostel Management</title>
</head>
<body>

	<%@ include file="Header.jsp"%>

	<%
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<form action="HostelCtl" method="post">

		<jsp:useBean id="bean" class="com.rays.bean.HostelBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1 align="center" style="color: navy;">

				<%
				if (bean != null && bean.getId() > 0) {
				%>
				Update
				<%
				} else {
				%>
				Add
				<%
				}
				%>

				Hostel

			</h1>

			<h3 align="center" style="color: red">
				<%=errorMsg != null ? errorMsg : ""%>
			</h3>

			<h3 align="center" style="color: green">
				<%=successMsg != null ? successMsg : ""%>
			</h3>

		</div>

		<input type="hidden" name="id"
			value="<%=bean.getId() > 0 ? bean.getId() : ""%>">

		<table align="center">

			<tr>
				<th>Name :</th>

				<td><input type="text" name="name"
					value="<%=bean.getName() != null ? bean.getName() : ""%>">
				</td>
			</tr>

			<tr>
				<th>Rooms :</th>

				<td><input type="text" name="rooms"
					value="<%=bean.getRooms() > 0 ? bean.getRooms() : ""%>"></td>
			</tr>

			<tr>
				<th>Warden Name :</th>

				<td><input type="text" name="wardenName"
					value="<%=bean.getWardenName() != null ? bean.getWardenName() : ""%>">
				</td>
			</tr>

			<tr>
				<th>Fees :</th>

				<td><input type="text" name="fees"
					value="<%=bean.getFees() > 0 ? bean.getFees() : ""%>"></td>
			</tr>

			<tr>
				<th></th>

				<td>
					<%
					if (bean != null && bean.getId() > 0) {
					%> <input type="submit" name="operation" value="Update"> <input
					type="submit" name="operation" value="Cancel"> <%
 } else {
 %> <input type="submit" name="operation" value="Save"> <input
					type="submit" name="operation" value="Reset"> <%
 }
 %>

				</td>
			</tr>

		</table>

	</form>

	<%@ include file="Footer.jsp"%>

</body>
</html>