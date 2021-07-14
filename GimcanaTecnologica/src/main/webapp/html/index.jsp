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
			<h1>Welcome page</h1>
			<p>If you are a game administrator, <a href="admin">login</a> to manage events.</p>
			<p>
				If you want to registry yourself or your team in a future event, please fill in the 
				<a href="events/registry">registration form.</a>
			</p>
			<c:if test="${events.size() > 0 }">
				<p>Here is the list for the next available events:</p>
				<table id="data-table" cellpadding="5" cellspacing="0">
					<tr>
						<th>Event date</th>
						<th>Event name</th>
					</tr>
					<c:forEach items="${events}" var="currentEvent">
						<tr>
							<td>${currentEvent.initDate}</td>
							<td>${currentEvent.name}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>
	

</body>
</html>