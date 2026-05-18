<%@page import="java.util.Iterator"%>
<%@page import="com.rays.bean.HostelBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hostel List</title>
</head>
<body>

	<%
	List<HostelBean> list = (List) request.getAttribute("list");
	List<HostelBean> nextList = (List) request.getAttribute("nextList");

	int pageNo = (int) request.getAttribute("pageNo");

	Iterator<HostelBean> it = list.iterator();

	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Hostel List</h1>

	<%
	if (list.size() == 0) {
	%>

	<h1 align="center" style="color: red">Record Not Found</h1>

	<%
	} else {
	%>

	<form action="HostelListCtl" method="post">

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

					<th>Name :</th>

					<td><input type="text" name="name"
						value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
						placeholder="search by name"></td>

					<th>Rooms :</th>

					<td><input type="text" name="rooms"
						value="<%=request.getParameter("rooms") != null ? request.getParameter("rooms") : ""%>"
						placeholder="search by rooms"></td>

					<th>Warden Name :</th>

					<td><input type="text" name="wardenName"
						value="<%=request.getParameter("wardenName") != null ? request.getParameter("wardenName") : ""%>"
						placeholder="search by warden name"></td>

					<th>Fees :</th>

					<td><input type="text" name="fees"
						value="<%=request.getParameter("fees") != null ? request.getParameter("fees") : ""%>"
						placeholder="search by fees"></td>

					<td><input type="submit" name="operation" value="Search">
					</td>

				</tr>

			</table>

			<table width="100%" border="1px">

				<tr>

					<th>Select</th>
					<th>Id</th>
					<th>Name</th>
					<th>Rooms</th>
					<th>Warden Name</th>
					<th>Fees</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {

					HostelBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=bean.getId()%></td>

					<td><%=bean.getName()%></td>

					<td><%=bean.getRooms()%></td>

					<td><%=bean.getWardenName()%></td>

					<td><%=bean.getFees()%></td>

					<td><a href="HostelCtl?id=<%=bean.getId()%>">Edit</a></td>

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