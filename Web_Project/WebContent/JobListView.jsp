<%@page import="java.util.Iterator"%>
<%@page import="com.rays.bean.JobBean"%>
<%@page import="java.util.List"%>
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
	List<JobBean> list = (List) request.getAttribute("list");
	List<JobBean> nextList = (List) request.getAttribute("nextList");
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<JobBean> it = list.iterator();
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Job List</h1>

	<form action="JobListCtl" method="post">

		<div align="center">
			<h2 style="color: green"><%=successMsg != null ? successMsg : ""%></h2>
			<h2 style="color: green"><%=errorMsg != null ? errorMsg : ""%></h2>
		</div>

		<%
		if (list.size() == 0) {
		%>
		<h1 style="color: red">Record Not Found</h1>
		<%
		} else {
		%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">
			<table width="90%" border="1px">
				<tr>
					<th>Select</th>
					<th>Id</th>
					<th>Job Code</th>
					<th>Job Name</th>
					<th>Cron Expression</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					JobBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value=<%=bean.getJobId()%>></td>
					<td><%=bean.getJobId()%></td>
					<td><%=bean.getJobCode()%></td>
					<td><%=bean.getJobName()%></td>
					<td><%=bean.getCronExpression()%></td>
					<td><%=bean.getStatus()%></td>
					<td><a href="JobAddCtl?jobId=<%=bean.getJobId()%>">Edit</a>
				</tr>
				<%
				}
				%>

			</table>
		</div>
		<div align="center">
			<table width="90%">
				<tr>
					<td><input type="submit" name="operation"
						<%=pageNo == 1 ? "disabled" : ""%> value="previous"></td>
					<td><input type="submit" name="operation" value="delete"></td>
					<td><input type="submit" name="operation"
						<%=nextList.size() == 0 ? "disabled" : ""%> value="next"></td>
				</tr>
			</table>
		</div>
		<%
		}
		%>
	</form>

</body>
</html>