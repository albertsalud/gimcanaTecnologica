<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Events list</h1>
			<p>
				<a href="<c:url value="/admin" />">&lt; Return home</a>
			</p>
			<p>
				<a href="<c:url value="/admin/events/new" />">&gt; Add new event</a>
			</p>
			<table id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Event name</th>
					<th>Event date</th>
					<th>Registry available</th>
					<th>Event started</th>
					<th></th>
				</tr>
				<c:forEach items="${listEvents}" var="currentEvent">
					<tr>
						<td>${currentEvent.name}</td>
						<td>${currentEvent.initDate}</td>
						<td>${currentEvent.allowPlayersRegistration}</td>
						<td>${currentEvent.eventStarted}</td>
						<td>
							<a href="<c:url value="/admin/events/${currentEvent.id}" />">&gt; Edit</a>
							<a href="<c:url value="/admin/events/${currentEvent.id}/players" />">&gt; Players list</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>