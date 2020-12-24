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
	<h1>Player status form</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<form:form method="post" modelAttribute="checkPointFormDTO" action="/players/checkPoint">
		<form:hidden path="locationCode" />
		<table>
			<tr>
				<td>Player name:</td>
				<td>
					<form:input path="playerName" />
					<form:errors path="playerName" cssClass="error" />
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