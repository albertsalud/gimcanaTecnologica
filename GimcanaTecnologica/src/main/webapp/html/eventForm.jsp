<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.error {
	color: red;
}
</style>
<meta charset="utf-8">
<title>Player Form</title>
<script>
$( function() {
	$( "#initDate" ).datepicker({
		dateFormat: "dd/mm/yy"
	});
} );
</script>
</head>
<body>
	<h1>Form to manage events</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<p>
		<a href="/admin/events">&lt; Return to events list</a>
	</p>
	<form:form method="post" modelAttribute="event" action="/admin/events/save">
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
					<input type="submit" value="Save" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>