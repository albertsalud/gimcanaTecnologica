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
		<a href="/admin">&lt; Return home</a>
	</p>
	<p>
		<a href="/admin/locations/new">&gt; Add new location</a>
	</p>
	<table>
		<tr>
			<th>Location name</th>
			<th>Location zone</th>
			<th></th>
		</tr>
		<c:forEach items="${locations}" var="currentLocation">
			<tr>
				<td>${currentLocation.name}</td>
				<td>${currentLocation.zone}</td>
				<td>
					<a href="/admin/locations/${currentLocation.id}">&gt; Edit</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>