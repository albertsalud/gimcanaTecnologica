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
			<h1>Event Players list</h1>
			<p>
				<a href="<c:url value="/admin/events" />">&lt; Return to events list</a>
			</p>
			<table id="data-table" cellspacing="0" cellpadding="5">
				<tr>
					<th>Player name</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${players}" var="currentPlayer">
					<tr>
						<td>${currentPlayer.name}</td>
						<td>
							<c:choose>
								<c:when test="${currentPlayer.present}">
									<a href="<c:url value="/admin/events/${currentPlayer.event.id}/players?player=${currentPlayer.id}&present=false" />">&gt; Mark as not present</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value="/admin/events/${currentPlayer.event.id}/players?player=${currentPlayer.id}&present=true" />">&gt; Mark as present</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${currentPlayer.present}">
								<a href="<c:url value="/admin/events/${currentPlayer.event.id}/players/${currentPlayer.id}" />">&gt; Progress</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>