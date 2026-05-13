<%@page import="java.util.Iterator"%>
<%@page import="com.rays.bean.ParkingBean"%>
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
	List<ParkingBean> list = (List) request.getAttribute("list");
	List<ParkingBean> nextList = (List) request.getAttribute("nextList");
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<ParkingBean> it = list.iterator();
	String successMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>

	<h1 align="center" style="color: darkblue">Parking List</h1>


	<%
	if (list.size() == 0) {
	%>
	<h1 style="color: red" align="center">Record Not Found</h1>
	<%
	} else {
	%>

	<form action="ParkingListCtl" method="post">

		<div align="center">
			<h3 style="color: green"><%=successMsg != null ? successMsg : ""%></h3>
			<h3 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h3>
		</div>

		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">

			<table>
				<tr>
					<th>Code:</th>
					<td><input type="text" name="code"
						value="<%=request.getParameter("code") != null ? request.getParameter("code") : ""%>"
						placeholder="search by Code"></td>
					<th>Vechile Number:</th>
					<td><input type="text" name="vechile_number"
						value="<%=request.getParameter("vechile_number") != null ? request.getParameter("vechile_number") : ""%>"
						placeholder="search by Vechile Number"></td>
					<th>Slot Number:</th>
					<td><input type="text" name="slot_number"
						value="<%=request.getParameter("slot_number") != null ? request.getParameter("slot_number") : ""%>"
						placeholder="search by Slot Number"></td>
					<th>Parking Code:</th>
					<td><input type="text" name="status"
						value="<%=request.getParameter("status") != null ? request.getParameter("status") : ""%>"
						placeholder="search by Status"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>

			<table width="100%" border="1px">
				<tr>
					<th>Select</th>
					<th>Id</th>
					<th>Code</th>
					<th>Vechile Number</th>
					<th>Slot Number</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					ParkingBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getCode()%></td>
					<td><%=bean.getVehicleNumber()%></td>
					<td><%=bean.getSlotNumber()%></td>
					<td><%=bean.getStatus()%></td>
					<td><a href="ParkingCtl?id=<%=bean.getId()%>">Edit</a></td>
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