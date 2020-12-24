<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Event Players List</title>
</head>
<body>
	<h1>Event Players list</h1>
	<p>
		<a href="/admin/eventos">&lt; Return to events list</a>
	</p>
	<table>
		<tr>
			<th>Player name</th>
			<th></th>
		</tr>
		<c:forEach items="${players}" var="currentPlayer">
			<tr>
				<td>${currentPlayer.name}</td>
				<td>
					<c:choose>
						<c:when test="${currentPlayer.present}">
							<a href="/admin/eventos/${currentPlayer.event.id}/players?player=${currentPlayer.id}&present=false">&gt; Mark as not present</a>
						</c:when>
						<c:otherwise>
							<a href="/admin/eventos/${currentPlayer.event.id}/players?player=${currentPlayer.id}&present=true">&gt; Mark as present</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>