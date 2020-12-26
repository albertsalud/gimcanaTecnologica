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
	<p>
		<a href="/admin/events/new">&gt; Add new event</a>
	</p>
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
					<a href="/admin/events/${currentEvent.id}">&gt; Edit</a>
					<a href="/admin/events/${currentEvent.id}/players">&gt; Players list</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>