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
<title>Location Form</title>
</head>
<body>
	<h1>Form to manage locations for events</h1>
	<c:if test="${message != null}">
		<h2 style="color:red"><c:out value="${message}" /></h2>
	</c:if>
	<p>
		<a href="/admin/locations">&lt; Return to locations list</a>
	</p>
	<form:form method="post" modelAttribute="locationFormDTO" action="/admin/locations/save">
		<form:hidden path="id"/>
		<table>
			<tr>
				<td>Location name:</td>
				<td>
					<form:input path="name" />
					<form:errors path="name" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Location code:</td>
				<td>
					<form:input path="code" />
					<form:errors path="code" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>
					<form:textarea path="description" />
					<form:errors path="description" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Zone:</td>
				<td>
					<form:select path="zone">
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Available:</td>
				<td>
					<form:checkbox path="available"/>
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