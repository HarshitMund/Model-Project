<%@page import="java.util.Iterator"%>
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
	List<SocialLinkBean> list = (List) request.getAttribute("list");
	List<SocialLinkBean> nextList = (List) request.getAttribute("nextList");
	
	int pageNo = (int) request.getAttribute("pageNo");
	Iterator<SocialLinkBean> it = list.iterator();
	String succesMsg = (String) request.getAttribute("successMsg");
	String errorMsg = (String) request.getAttribute("errorMsg");
	%>

	<%@ include file="Header.jsp"%>


	<h1 align="center" style="color: darkblue">
		<u>Social Link List</u>
	</h1>

	<form action="SocialLinkListCtl" method="post">

		<div align="center">
			<h2 style="color: green"><%=succesMsg != null ? succesMsg : ""%></h2>
			<h2 style="color: red"><%=errorMsg != null ? errorMsg : ""%></h2>
		</div>
		<%
		if (list.size() == 0) {
		%>
		<h1 style="color: red">Record Not found</h1>
		<%
		} else {
		%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>">

		<div align="center">

			<table>
				<tr>
					<th>Social Code</th>
					<td><input type="text" name="socialCode"
						value="<%=request.getParameter("socialCode") != null ? request.getParameter("socialCode") : ""%>"
						placeholder="search by socialCode"></td>
					<th>User Name</th>
					<td><input type="text" name="userName"
						value="<%=request.getParameter("userName") != null ? request.getParameter("userName") : ""%>"
						placeholder="search by userName"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>

			<table width="100%" border="1px">
				<tr style="background-color: skyblue">
					<th>Select</th>
					<th>Social Id</th>
					<th>Social Code</th>
					<th>User Name</th>
					<th>Platform</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					SocialLinkBean bean = it.next();
				%>

				<tr align="center" style="background-color: #D3D3D3;">
					<td><input type="checkbox" name="ids" value=<%=bean.getSocialId()%>></td>
					<td><%=bean.getSocialId()%></td>
					<td><%=bean.getSocialCode()%></td>
					<td><%=bean.getUserName()%></td>
					<td><%=bean.getPlatform()%></td>
					<td><%=bean.getStatus()%></td>
					<td><a href="AddUserCtl?id=<%=bean.getSocialId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
		<div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						<%=pageNo == 1 ? "disabled" : ""%> value="previous"></td>
					<td><input type="submit" name="operation" value="delete"></td>
					<td align="right"><input type="submit" name="operation"
						<%=nextList.size() == 0 ? "disabled" : ""%> value="next"></td>
				</tr>
			</table>
		</div>
		<%
		}
		%>
	</form>

	<%@ include file="Footer.jsp"%>

</body>
</html>