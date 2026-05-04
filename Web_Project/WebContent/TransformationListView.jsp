<%@page import="java.util.Iterator"%>
<%@page import="com.rays.bean.TransformationBean"%>
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
	List<TransformationBean> list = (List) request.getAttribute("list");
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<TransformationBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Transformation List</h1>

	<form action="TransformationListCtl" method="post">

		<%
		if (list.size() == 0) {
		%>
		<h1 style="color: red" align="center">Record Not Found</h1>
		<%
		} else {
		%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">
			<table width="100%" border="1px">
				<tr>
					<th>Id</th>
					<th>Code</th>
					<th>Name</th>
					<th>Logic</th>
					<th>Status</th>
				</tr>

				<%
				while (it.hasNext()) {
					TransformationBean bean = it.next();
				%>
				<tr align="center">
					<td><%=bean.getId()%></td>
					<td><%=bean.getCode()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getLogic()%></td>
					<td><%=bean.getStatus()%></td>
				</tr>
				<%
				}
				%>

			</table>
		</div>
		
		<div>
		</div>

		<%
		}
		%>
	</form>


</body>
</html>