<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Location List</title>
</head>
<body>
	<h1>Location list</h1>
	<p>
		<a href="/admin/localizaciones/nueva">&gt; Add new location</a>
	</p>
	<table>
		<tr>
			<th>Location name</th>
			<th></th>
		</tr>
		<c:forEach items="${locations}" var="currentLocation">
			<tr>
				<td>${currentLocation.name}</td>
				<td>
					<a href="/admin/localizaciones/${currentLocation.id}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>