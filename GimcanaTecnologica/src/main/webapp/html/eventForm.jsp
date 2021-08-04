<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<script>
	$( function() {
		$( "#initDate" ).datepicker({
			dateFormat: "dd/mm/yy"
		});
	} );
	</script>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Event form</h1>
			<c:if test="${message != null}">
				<h2 class="error"><c:out value="${message}" /></h2>
			</c:if>
			<p>
				<a href="<c:url value="/admin/events" />">&lt; Return to events list</a>
			</p>
			<form:form method="post" modelAttribute="eventFormDTO" action="./save">
				<form:hidden path="id"/>
				<table>
					<tr>
						<td>Event name:</td>
						<td>
							<form:input path="name" />
							<form:errors path="name" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>Event date (dd/MM/yyyy):</td>
						<td>
							<form:input path="initDate" />
							<form:errors path="initDate" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td>Allow players registration:</td>
						<td>
							<form:checkbox path="allowPlayersRegistration" />
							<form:errors path="allowPlayersRegistration" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>Enable start game:</td>
						<td>
							<form:checkbox path="eventStarted" />
							<form:errors path="eventStarted" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Save" class="boton"/>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>