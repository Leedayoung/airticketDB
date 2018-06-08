<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Update User Info</title>
</head>
<body>
<form method="get" action="/airticketDB/UpdateUserInfo">
	<input type="hidden" name="CID" value="${param.CID}">
	Name: <input type="text" name="CName"/> <br>
	Age: <input type="number" name="CAge"/> <br>
	Gender <br>
	<input type="radio" name="CGender" value="male"/> Male<br>
	<input type="radio" name="CGender" value="female"/> Female<br>
	Nationality: <input type="text" name="CNation"/> <br>
	<input type= "submit" value="Update"/>
</form>
</body>
</html>