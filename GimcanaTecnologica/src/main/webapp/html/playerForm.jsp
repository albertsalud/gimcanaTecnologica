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
<title>Event Form</title>
</head>
<body>
	<h1>Registration form</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<form:form method="post" modelAttribute="player" action="/eventos/registro">
		<form:hidden path="id"/>
		<form:hidden path="secretWord" />
		<table>
			<tr>
				<td>Event:</td>
				<td>
					<form:select path="event" items="${events}" itemLabel="name" itemValue="id">
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Player name:</td>
				<td>
					<form:input path="name" />
					<form:errors path="name" cssClass="error" />
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