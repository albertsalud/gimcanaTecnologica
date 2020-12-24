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
<meta charset="utf-8">
<title>Player Form</title>
</head>
<body>
	<h1>Form to manage events</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<p>
		<a href="/admin/eventos">&lt; return to events list</a>
	</p>
	<form:form method="post" modelAttribute="event" action="/admin/eventos/guardar">
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