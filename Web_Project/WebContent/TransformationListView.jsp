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
	List<TransformationBean> nextList = (List) request.getAttribute("nextList");
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<TransformationBean> it = list.iterator();
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Transformation List</h1>


	<%
	if (list.size() == 0) {
	%>
	<h1 style="color: red" align="center">Record Not Found</h1>
	<%
	} else {
	%>

	<form action="TransformationListCtl" method="post">

		<div align="center">
			<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>
		</div>

		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">

			<table>
				<tr>
					<th>Transformation Name:</th>
					<td><input type="text" name="name"
						value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
						placeholder="search by Name"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>

			<table width="100%" border="1px">
				<tr>
					<th>Select</th>
					<th>Id</th>
					<th>Code</th>
					<th>Name</th>
					<th>Logic</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					TransformationBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getCode()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getLogic()%></td>
					<td><%=bean.getStatus()%></td>
					<td><a href="TransformationAddCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>

			</table>
		</div>

		<div>
			<table width="100%">
				<tr>
					<td align="left"><input type="submit" name="operation"
						<%=pageNo == 1 ? "disabled" : ""%> value="Previous"></td>
					<td align="center"><input type="submit" name="operation"
						value="Delete"></td>
					<td align="right"><input type="submit" name="operation"
						<%=nextList.size() == 0 ? "disabled" : ""%> value="Next"></td>
				</tr>
			</table>
		</div>

		<%
		}
		%>
	</form>


</body>
</html>