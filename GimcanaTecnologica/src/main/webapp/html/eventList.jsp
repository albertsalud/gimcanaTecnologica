<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
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
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${listEvents}" var="currentEvent">
					<tr>
						<td>${currentEvent.name}</td>
						<td>
							<fmt:formatDate value="${currentEvent.initDate}" pattern="dd/MM/yyyy HH:mm"/>
						</td>
						<td align="center">
							<c:if test="${currentEvent.allowPlayersRegistration}">
								<img src="<c:url value="/images/checked.png" />" width="24"/>
							</c:if>
							<c:if test="${!currentEvent.allowPlayersRegistration}">
								<img src="<c:url value="/images/unchecked.png" />" width="24" />
							</c:if>
						</td>
						<td align="center">
							<c:if test="${currentEvent.eventStarted}">
								<img src="<c:url value="/images/checked.png" />" width="24"/>
							</c:if>
							<c:if test="${!currentEvent.eventStarted}">
								<img src="<c:url value="/images/unchecked.png" />" width="24" />
							</c:if>
						</td>
						<td>
							<a href="<c:url value="/admin/events/${currentEvent.id}" />">&gt; Edit</a>
						</td>
						<td>
							<a href="<c:url value="/admin/locations/print/${currentEvent.id}" />">&gt; Print locations</a>
						</td>
						<td>
							<a href="<c:url value="/admin/events/${currentEvent.id}/players" />">&gt; Players list</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>