<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
.error {
	color: red;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

function getEventPlayers(){
	var selectedEventId = $("#eventId").val();
	if(selectedEventId != "") {
		$.ajax({
			  method: "GET",
			  url: "/players-rest/eventPlayers",
			  data: {eventId:selectedEventId}
		})
		.done(function( players ) {
			$("#playerId").empty();
			$(players).each(function(index, player){
				$("#playerId").append(new Option(player.name, player.id));
			});
		});
	
	} else {
		$("#playerId").empty();
		$("#playerId").append(new Option("- Select an event-", ""));
	}
	
}
</script>
<meta charset="utf-8">
<title>Player Form</title>
</head>
<body>
	<h1>Player status form</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<form:form method="post" modelAttribute="checkPointFormDTO" action="/players/checkPoint">
		<form:hidden path="locationCode" />
		<table>
			<tr>
				<td>Event:</td>
				<td>
					<form:select path="eventId" onchange="getEventPlayers()">
						<c:if test="${events.size() > 1 }">
							<form:option value="">- Available events -</form:option>
						</c:if>
						<form:options items="${events}" itemLabel="fullEventName" itemValue="id"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Player:</td>
				<td>
					<c:choose>
						<c:when test="${playersList != null}">
							<form:select path="playerId" items="${playersList}" itemValue="id" itemLabel="name">
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="playerId" >
								<form:option value="">- Select an event -</form:option>
							</form:select>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<form:password path="password" />
					<form:errors path="password" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Check" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>