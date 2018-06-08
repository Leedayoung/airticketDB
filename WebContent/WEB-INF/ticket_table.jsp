<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Tickets</title>
</head>
<body>
Hello ${CName}! You are ${param.CID}. <br>
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
		<c:forEach items="${tickets}" var="ticket">
			<tr>
				<td>${ticket.airportDName}</td>
				<td>${ticket.ticketDTime}</td>
				<td>${ticket.airportAName}</td>
				<td>${ticket.ticketATime}</td>
				<td>${ticket.ticketSeat}</td>
				<td>${ticket.airlineName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>