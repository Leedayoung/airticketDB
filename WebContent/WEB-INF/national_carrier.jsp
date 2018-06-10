<%@ page language="java" import="java.util.List, data.Airline" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<title>National Carriers</title>
</head>
<body>
National carriers for your country.
<table border="1">
	<thead>
		<tr>
			<th> Nationality </th>
			<th> Airline Name </th>
			<th> Alliance </th>
		</tr>
	</thead>
	<tbody>
		<%
			@SuppressWarnings("unchecked")
			List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
			for (Airline airline : airlines) {
		%>
			<tr>
				<td><%= airline.getNation() %></td>
				<td><%= airline.getName() %></td>
				<td><%= airline.getAlliance() %></td>
			</tr>
		<% } %>
	</tbody>
</table>
</body>
</html>