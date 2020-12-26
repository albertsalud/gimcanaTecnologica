<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Event Player Status</title>
</head>
<body>
	<h1>Event Player Status</h1>
	<p>
		<a href="/admin/events/${player.event.id}/players">&lt; Return to event player list</a>
	</p>
	<p>
		Player: ${player.name}
	<p>
	<p>CheckPoints:</p>
	<table>
		<tr>
			<th>Location</th>
			<th>Assigned</th>
			<th>Checked</th>
		</tr>
		<c:forEach items="${player.checkPoints}" var="currentCheckPoint">
			<tr>
				<td>${currentCheckPoint.location.name}</td>
				<td>${currentCheckPoint.assignedDate}</td>
				<td>${currentCheckPoint.checkedDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>