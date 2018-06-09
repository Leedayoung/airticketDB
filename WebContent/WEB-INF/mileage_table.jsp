<%@ page language="java" import="java.util.List,data.Alliance" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mileage Table</title>
</head>
<body>
Your Mileages having 1000 points or more. <br>
<table border="1">
	<thead>
		<tr>
			<th> Airline Alliance </th>
			<th> Mileage Sum </th>
		</tr>
	</thead>
	<tbody>
		<%
			@SuppressWarnings("unchecked")
			List<Alliance> alliances = (List<Alliance>) request.getAttribute("alliances");	
			for (Alliance alliance : alliances) {
		%>
			<tr>
				<td><%= alliance.getName() %></td>
				<td><%= alliance.getMileage() %></td>
			</tr>
		<% } %>
	</tbody>
</table>
</body>
</html>