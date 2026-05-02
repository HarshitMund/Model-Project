<%@page import="com.rays.bean.JobBean"%>
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
	JobBean bean = (JobBean) request.getAttribute("bean");
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>
			<%=bean != null ? "Update Job" : "Job Add"%>
		</h1>
		<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
		<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>

		<form action="JobAddCtl" method="post">

			<input type="hidden" name="id"
				value="<%=bean != null ? bean.getJobId() : ""%>">
			<table>
				<tr>
					<th>Job Code:</th>
					<td><input type="text" name="jobCode"
						value="<%=bean != null ? bean.getJobCode() : ""%>"
						placeholder="Enter Job Code"></td>
				</tr>

				<tr>
					<th>Job Name:</th>
					<td><input type="text" name="jobName"
						value="<%=bean != null ? bean.getJobName() : ""%>"
						placeholder="Enter Job Name"></td>
				</tr>

				<tr>
					<th>Cron Expression:</th>
					<td><input type="text" name="cronExpression"
						value="<%=bean != null ? bean.getCronExpression() : ""%>"
						placeholder="Enter Job Code"></td>
				</tr>

				<tr>
					<th>Status:</th>
					<td><input type="text" name="status"
						value="<%=bean != null ? bean.getStatus() : ""%>"
						placeholder="Enter Status"></td>
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