<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
<script type="text/javascript">

function getEventPlayers(){
	var selectedEventId = $("#eventId").val();
	if(selectedEventId != "") {
		$.ajax({
			  method: "GET",
			  url: "<c:url value="/players-rest/eventPlayers" />",
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
		$("#playerId").append(new Option("- Selecciona una gimcana -", ""));
	}
	
}
</script>
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Punt de control</h1>
			<c:if test="${message != null}">
				<h2 style="color:red"><c:out value="${message}" /></h2>
			</c:if>
			<form:form method="post" modelAttribute="checkPointFormDTO" action="checkPoint">
				<form:hidden path="locationCode" />
				<table>
					<tr>
						<td>Gimcana:</td>
						<td>
							<form:select path="eventId" onchange="getEventPlayers()">
								<c:if test="${events.size() > 1 }">
									<form:option value="">- Gimcanes -</form:option>
								</c:if>
								<form:options items="${events}" itemLabel="fullEventName" itemValue="id"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Jugador / Equip:</td>
						<td>
							<c:choose>
								<c:when test="${playersList != null}">
									<form:select path="playerId" items="${playersList}" itemValue="id" itemLabel="name">
									</form:select>
								</c:when>
								<c:otherwise>
									<form:select path="playerId" >
										<form:option value="">- Selecciona una gimcana -</form:option>
									</form:select>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>Paraula de pas:</td>
						<td>
							<form:password path="password" />
							<form:errors path="password" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Comprova" class="boton"/>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>