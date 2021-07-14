<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Player status</h1>
			<c:if test="${resultMessage != null }">
				<p>${resultMessage}</p>
			</c:if>
			<p id="secretWord">Your secret word is: ${charactersFound}</p>
			<p>
				${descriptionMessage}
			</p>
		</div>
	</div>
</body>
</html>