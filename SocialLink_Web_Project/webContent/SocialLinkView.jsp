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
	String succesMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	SocialLinkBean bean = (SocialLinkBean) request.getAttribute("bean");
	%>

	<%@ include file="Header.jsp"%>
	<div align="center">

		<h1><%=bean != null ? "Update Social Link" : "Add Social Link"%></h1>

		<h3 style="color: green"><%=succesMsg != null ? succesMsg : ""%></h3>
		<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>
		<form action="AddUserCtl" method="post">

			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getSocialId() : ""%>">

			<table>

				<tr>
					<th>Social Code :</th>
					<td><input type="text" name="socialCode"
						value="<%=bean != null ? bean.getSocialCode() : ""%>"
						placeholder="enter social code"></td>
				</tr>

				<tr>
					<th>User Name :</th>
					<td><input type="text" name="userName"
						value="<%=bean != null ? bean.getUserName() : ""%>"
						placeholder="enter user name"></td>
				</tr>

				<tr>
					<th>Platform :</th>
					<td><input type="text" name="platform"
						value="<%=bean != null ? bean.getPlatform() : ""%>"
						placeholder="enter your platform"></td>
				</tr>

				<tr>
					<th>Status :</th>
					<td><input type="text" name="status"
						value="<%=bean != null ? bean.getStatus() : ""%>"
						placeholder="enter your status"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=bean != null ? "update" : "save"%>"></td>
				</tr>

			</table>

		</form>

	</div>
	<%@ include file="Footer.jsp"%>


</body>
</html>