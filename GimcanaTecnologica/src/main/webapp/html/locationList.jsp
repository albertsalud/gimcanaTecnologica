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
		<div id="content" class="admin">
			<h1>Locations list</h1>
			<p>
				<a href="<c:url value="/admin" />">&lt; Return home</a>
			</p>
			<p>
				<a href="<c:url value="/admin/locations/new" />">&gt; Add new location</a>
			</p>
			<table id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Location name</th>
					<th>Location zone</th>
					<th></th>
				</tr>
				<c:forEach items="${locations}" var="currentLocation">
					<tr>
						<td>${currentLocation.name}</td>
						<td>${currentLocation.zone}</td>
						<td>
							<a href="<c:url value="/admin/locations/${currentLocation.id}" />">&gt; Edit</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>