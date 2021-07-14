<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1>Welcome to admins private area</h1>
			<p>
				From this site you can manage:
				<ul>
				<li><a href="<c:url value="/admin/events" />"><b>Events</b></a>: create and manage events, players, checkpoints...</li>
				<li><a href="<c:url value="/admin/locations" />"><b>Locations</b></a>: manage available locations for events</li>
				</ul>
			</p>
			<p><a href="logout">&lt; Logout</a></p>
		</div>
	</div>
</body>
</html>