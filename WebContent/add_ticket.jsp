<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Add Ticket</title>
</head>
<body>
<form method="get" action="/airticketDB/AddTicket">
	<input type="hidden" name="CID" value="${param.CID}">
	The departure airport location city: <input type="text" name="PDCity"/> <br>
	The departure date: <input type="date" name="TDDate"/> <br>
	The departure time: <input type="time" name="TDTime"/> <br>
	The arrival airport location city: <input type="text" name="PACity"/> <br>
	The arrival date: <input type="date" name="TADate"/> <br>
	The arrival time: <input type="time" name="TATime"/> <br>
	The airline name: <input type="text" name="LName"/> <br>
	The seat number: <input type="number" name="TSeat"/> <br>
	The price of the airticket: <input type="number" name="TPrice"/> EUR<br>
	<input type="submit" value="Add Ticket"/>
</form>
</body>
</html>