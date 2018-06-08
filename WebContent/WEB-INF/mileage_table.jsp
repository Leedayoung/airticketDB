<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Mileage Table</title>
</head>
<body>
Your Mileages. <br>
<table border="1">
	<thead>
		<tr>
			<th> Airline Union </th>
			<th> Mileage Sum </th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${unions}" var="union">
			<tr>
				<td>${union.unionName}</td>
				<td>${union.mileage}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>