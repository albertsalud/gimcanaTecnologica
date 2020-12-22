<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Event List</title>
</head>
<body>
	<h1>Event list</h1>
	<table>
		<tr>
			<th>Event name</th>
			<th>Event date</th>
			<th></th>
		</tr>
		<c:forEach items="${listEvents}" var="currentEvent">
			<tr>
				<td>${currentEvent.name}</td>
				<td>${currentEvent.initDate}</td>
				<td>
					<a href="/admin/eventos/${currentEvent.id}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>