<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Delete Ticket</title>
</head>
<body>
<form method="get" action="/airticketDB/DeleteTicket">
	<input type="hidden" name="CID" value="${param.CID}">
	The departure airport location city: <input type="text" name="PDCity"/> <br>
	The departure date: <input type="date" name="TDDate"/> <br>
	The departure time: <input type="time" name="TDTime"/> <br>
	<input type="submit" value="Delete Ticket"/>
</form>
</body>
</html>