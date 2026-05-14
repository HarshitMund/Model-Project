<%@page import="java.util.Iterator"%>
<%@page import="com.rays.bean.GamingBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gaming Tournament List</title>
</head>
<body>

	<%
	List<GamingBean> list = (List) request.getAttribute("list");
	List<GamingBean> nextList = (List) request.getAttribute("nextList");

	int pageNo = (int) request.getAttribute("pageNo");

	Iterator<GamingBean> it = list.iterator();

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Gaming Tournament List</h1>

	<%
	if (list.size() == 0) {
	%>

	<h1 align="center" style="color: red">Record Not Found</h1>

	<%
	} else {
	%>

	<form action="GamingListCtl" method="post">

		<div align="center">

			<h3 style="color: green">
				<%=successMsg != null ? successMsg : ""%>
			</h3>

			<h3 style="color: red">
				<%=errorMsg != null ? errorMsg : ""%>
			</h3>

		</div>

		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">

			<table>

				<tr>

					<th>Code :</th>

					<td><input type="text" name="code"
						value="<%=request.getParameter("code") != null ? request.getParameter("code") : ""%>"
						placeholder="search by code"></td>

					<th>Name :</th>

					<td><input type="text" name="name"
						value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
						placeholder="search by name"></td>

					<th>Prize Pool :</th>

					<td><input type="text" name="prizePool"
						value="<%=request.getParameter("prizePool") != null ? request.getParameter("prizePool") : ""%>"
						placeholder="search by prize pool"></td>

					<th>Status :</th>

					<td><input type="text" name="status"
						value="<%=request.getParameter("status") != null ? request.getParameter("status") : ""%>"
						placeholder="search by status"></td>

					<td><input type="submit" name="operation" value="Search">
					</td>

				</tr>

			</table>

			<table width="100%" border="1px">

				<tr>

					<th>Select</th>
					<th>Id</th>
					<th>Code</th>
					<th>Name</th>
					<th>Prize Pool</th>
					<th>Status</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {

					GamingBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=bean.getId()%></td>

					<td><%=bean.getCode()%></td>

					<td><%=bean.getName()%></td>

					<td><%=bean.getPrizePool()%></td>

					<td><%=bean.getStatus()%></td>

					<td><a href="GamingCtl?id=<%=bean.getId()%>">Edit</a></td>

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
						<%=nextList.size() == 0 ? "disabled" : ""%> value="Next">

					</td>

				</tr>

			</table>

		</div>

	</form>

	<%
	}
	%>

</body>
</html>