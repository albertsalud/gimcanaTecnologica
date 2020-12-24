<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Player status</title>
</head>
<body>
	<h1>Player status</h1>
	<c:if test="${resultMessage != null }">
		<p>${resultMessage}</p>
	</c:if>
	<p id="secretWord">Your secret word is: ${charactersFound}</p>
	<p>
		${descriptionMessage}
	</p>
</body>
</html>