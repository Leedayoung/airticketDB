<%@ page language="java" import="java.util.List,data.Alliance" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Mileage Table</title>
</head>
<body>
Your Mileages having 100 points or more. <br>
<table border="1">
	<thead>
		<tr>
			<th> Airline Alliance </th>
			<th> Mileage Sum </th>
		</tr>
	</thead>
	<tbody>
		<%
			List<Alliance> unions = (List<Alliance>) request.getAttribute("alliances");	
			for (Alliance union : unions) {
		%>
			<tr>
				<td><%= union.getName() %></td>
				<td><%= union.getMileage() %></td>
			</tr>
		<% } %>
	</tbody>
</table>
</body>
</html>