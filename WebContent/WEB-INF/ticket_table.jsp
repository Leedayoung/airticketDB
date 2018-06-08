<%@ page language="java" import="java.util.List, data.Ticket" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Tickets</title>
</head>
<body>
Hello ${CName}! You are ${param.CID}. <br>
<a href="/airticketDB/add_ticket.jsp?CID=${param.CID}">Add Ticket</a> 
<a href="/airticketDB/delete_ticket.jsp?CID=${param.CID}">Delete Ticket</a> 
<a href="/airticketDB/update_user_info.jsp?CID=${param.CID}">Change User Information</a>
<a href="/airticketDB/MileageTable?CID=${param.CID}">Check Your Mileages</a>
<table border="1">
	<thead>
		<tr>
			<th> Departure Airport </th>
			<th> Departure Time </th>
			<th> Arrival Airport </th>
			<th> Arrival Time </th>
			<th> Seat Number </th>
			<th> Airline Name </th>
		</tr>
	</thead>
	<tbody>
		<%
			List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");	
			for (Ticket ticket : tickets) {
		%>
			<tr>
				<td><%= ticket.getAirportDName() %></td>
				<td><%= ticket.getTicketDTime() %></td>
				<td><%= ticket.getAirportAName() %></td>
				<td><%= ticket.getTicketATime() %></td>
				<td><%= ticket.getTicketSeat() %></td>
				<td><%= ticket.getAirlineName() %></td>
			</tr>
		<% } %>
	</tbody>
</table>
</body>
</html>