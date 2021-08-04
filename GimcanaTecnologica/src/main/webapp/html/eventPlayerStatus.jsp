<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Event Player Status</h1>
			<p>
				<a href="<c:url value="/admin/events/${player.event.id}/players" />">&lt; Return to event player list</a>
			</p>
			<p>
				Player: ${player.name}
			<p>
			<p>CheckPoints:</p>
			<table id="data-table" cellpadding="5" cellspacing="0">
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
		</div>
	</div>
</body>
</html>