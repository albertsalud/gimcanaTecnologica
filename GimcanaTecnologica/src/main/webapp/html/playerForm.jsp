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
	<h1>Registration form</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<p>
		<a href="/">&lt; Return main page</a>
	</p>
	<form:form method="post" modelAttribute="player" action="/events/registry">
		<form:hidden path="id"/>
		<form:hidden path="secretWord" />
		<table>
			<tr>
				<td>Event:</td>
				<td>
					<form:select path="event" items="${events}" itemLabel="fullEventName" itemValue="id">
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
				<td>Player password:</td>
				<td>
					<form:input path="password" />
					<form:errors path="password" cssClass="error" />
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